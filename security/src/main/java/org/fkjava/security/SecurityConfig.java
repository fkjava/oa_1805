package org.fkjava.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fkjava.common.data.domain.Result;
import org.fkjava.menu.domain.Menu;
import org.fkjava.menu.service.MenuService;
import org.fkjava.security.domain.UserDetails;
import org.fkjava.security.interceptors.UserHolderInterceptor;
import org.fkjava.security.service.SecurityService;
import org.fkjava.security.service.impl.MyAccessControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("org.fkjava")
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);
	@Autowired
	private SecurityService securityService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private static final String REMEMBER_KEY = "fkjava.secure.keys";

	@Autowired
	private MenuService menuService;
	// Spring Boot里面已经配置好了ObjectMapper
	// 没有Spring Boot则需要手动加入Spring MVC的配置里面
	@Autowired
	private ObjectMapper objectMapper;

	// 自定义AuthenticationProvider，不隐藏【用户未找到的异常】
	// Spring Security会默认自动创建AuthenticationProvider
	// 但是如果开发者自己提供了，那么就不会自动创建
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider(//
//			SecurityService securityService, //
//			PasswordEncoder passwordEncoder) {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setHideUserNotFoundExceptions(false);
//		provider.setUserDetailsService(securityService);
//		provider.setPasswordEncoder(passwordEncoder);
//
//		return provider;
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 不要调用super.configure(auth)方法
		// 如果调用了，Spring会自动创建一个DaoAuthenticationProvider
		// 具体创建的地方在InitializeUserDetailsBeanManagerConfigurer类里面
		// 代码执行路径是从WebSecurityConfigurerAdapter.authenticationManager()进去的。
//		super.configure(auth);

		// 此时DaoAuthenticationProvider不会被Spring容器管理，而是直接注入到AuthenticationManagerBuilder里面
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setHideUserNotFoundExceptions(false);
		provider.setUserDetailsService(securityService);
		provider.setPasswordEncoder(passwordEncoder);

		auth.authenticationProvider(provider);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserHolderInterceptor())//
				.addPathPatterns("/**")//
		// 默认Spring Security的拦截器，已经在其他的拦截器之前
		// 所以不使用order也是可以有效的
		// 如果不能正常获取到User（通过UserHolder），那么就需要修改顺序。
		// .order(Integer.MAX_VALUE)// 排在最后
		;
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();

				postLogin(request, userDetails);

				String accept = request.getHeader("Accept");
				if (accept != null && (accept.contains("application/json") || accept.contains("text/javascript"))) {
					// 返回JSON
					Result result = Result.ok("登录成功，请引导用户进入首页");
					response.setContentType("application/json; charset=UTF-8");
					PrintWriter out = response.getWriter();
					objectMapper.writeValue(out, result);
				} else {
					// 执行默认的登录成功操作
					super.onAuthenticationSuccess(request, response, authentication);
				}
			}
		};
		return successHandler;
	};

	/**
	 * 登录以后，进行一些后处理，用于加载用户专属信息
	 * 
	 * @param request
	 * @param userDetails
	 */
	private void postLogin(HttpServletRequest request, UserDetails userDetails) {
		HttpSession session = request.getSession();
		// 获取用户有权访问的所有URL
		Set<String> urls = menuService.findMyUrls(userDetails.getUserId());

		// 获取用户左侧的菜单树
		List<Menu> menus = menuService.findMyMenus(userDetails.getUserId());
		// 尝试转换所有菜单为JSON，为了避免每次访问生成一次
		String menusJson;
		try {
			menusJson = objectMapper.writeValueAsString(menus);
		} catch (JsonProcessingException e) {
			LOG.error("无法把用户的菜单转换为JSON: " + e.getLocalizedMessage(), e);
			menusJson = "[]";
		}
		// 用户的菜单存储到Session里面
		session.setAttribute("menusJson", menusJson);
		session.setAttribute("urls", urls);
	}

	// 配置基于HTTP的安全控制
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String loginPage = "/security/login";
		// 处理登录失败的时候的任务
		SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler(
				loginPage + "?error") {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				request.getSession().setAttribute("loginName", request.getParameter("loginName"));

				String accept = request.getHeader("Accept");
				if (accept != null && (accept.contains("application/json") || accept.contains("text/javascript"))) {
					// 返回JSON
					Result result = Result.error("登录失败，因为：" + exception.getLocalizedMessage());
					response.setContentType("application/json; charset=UTF-8");
					PrintWriter out = response.getWriter();
					objectMapper.writeValue(out, result);
				} else {
					// 在重定向之前，先把登录名放Session
					super.onAuthenticationFailure(request, response, exception);
				}
			}
		};

		http.authorizeRequests()// 验证请求
				// 登录页面的地址和其他的静态页面都不要权限
				// /*表示目录下的任何地址，但是不包括子目录
				// /** 则连同子目录一起匹配
				.antMatchers(loginPage, "/error/**", "/layout/ex", "/images/**", "/css/**", "/zTree/**", "/js/**",
						"/webjars/**", "/static/**")//
				.permitAll()// 不做访问判断
				.antMatchers("/", "/index").authenticated()// 授权以后才能访问，但不使用自定义检查
				.anyRequest()// 其他所有请求
				.access("authenticated && @myAccessControl.check(authentication,request)")// 自定义检查用户是否有权限访问
				.and()// 并且
				.formLogin()// 使用表单进行登录
				.loginPage(loginPage)// 登录页面的位置，默认是/login
				// 此页面不需要有对应的JSP，而且也不需要有对应代码，只要URL
				// 这个URL是Spring Security使用的，用来接收请求参数、调用Spring Security的鉴权模块
				.loginProcessingUrl("/security/do-login")// 处理登录请求的URL
				.successHandler(successHandler())// 登录成功以后的处理器
				// .defaultSuccessUrl(defaultSuccessUrl)
				// 在登录成功以后，会判断Session里面是否有记录之前访问的URL，如果有则使用之前的URL继续访问
				// 如果没有则使用defaultSuccessUrl
				// .defaultSuccessUrl("/index")//默认的登录成功页面
				.usernameParameter("loginName")// 登录名的参数名
				.passwordParameter("password")// 密码的参数名称
				.failureHandler(failureHandler)// 登录失败以后的处理器
//				.failureForwardUrl("/security/login")//
				.and().logout()// 配置退出登录
				.logoutUrl("/security/do-logout")
				// .logoutSuccessUrl("/")
				// .and().httpBasic()// 也可以基于HTTP的标准验证方法（弹出对话框）
				.and().csrf()// 激活防跨站攻击功能
				.and().rememberMe()// 记住登录状态
				.useSecureCookie(true)//
				.userDetailsService(securityService)//
				.rememberMeServices(rememberMeServices())//
				.key(REMEMBER_KEY)//
		;
	}

	@Bean
	public RememberMeServices rememberMeServices() {
		TokenBasedRememberMeServices rememberMeServices = //
				new TokenBasedRememberMeServices(REMEMBER_KEY, securityService) {
					@Override
					protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
							HttpServletResponse response) {

						// 根据Cookie自动登录
						UserDetails userDetails = (UserDetails) super.processAutoLoginCookie(cookieTokens, request,
								response);

						// 登录成功以后，把用户的菜单和权限获取出来
						postLogin(request, userDetails);

						return userDetails;
					}
				};
		return rememberMeServices;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 系统默认自动把静态文件的根目录，映射到/public、/static、/resources里面。
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 动态注册URL和视图的映射关闭，解决控制器里面几乎没有代码的问题。
//		registry.addViewController("/security/login")//
//				.setStatusCode(HttpStatus.UNAUTHORIZED)//
//				.setViewName("security/login");

		registry.addViewController("/index").setViewName("security/index");
		// 欢迎页，访问根目录重定向到一个首页
		// registry.addViewController("/").setViewName("security/index");
		registry.addRedirectViewController("/", "/index");
	}

	@Bean
	public MyAccessControl myAccessControl() {
		return new MyAccessControl();
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityConfig.class, args);
	}
}
