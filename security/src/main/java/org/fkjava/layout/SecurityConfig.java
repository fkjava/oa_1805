package org.fkjava.layout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("org.fkjava")
public class SecurityConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 系统默认自动把静态文件的根目录，映射到/public、/static、/resources里面。
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 动态注册URL和视图的映射关闭，解决控制器里面几乎没有代码的问题。
		registry.addViewController("/security/login")//
				.setViewName("security/login");
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityConfig.class, args);
	}
}
