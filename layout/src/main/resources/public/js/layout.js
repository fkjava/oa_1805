$(document).ready(function() {
	// 显示或者隐藏侧边栏
	$('[data-toggle="sidebar"]').click(function() {
		$('.sidebar').toggleClass('active')
	});
	
	// 获取当前用户的菜单，并显示出来
	$.ajax({
		url : contextPath + "/menu/menus",
		method: "GET",
		dataType: "JSON",
		success: function(menus, status, xhr){
			// 显示菜单
			for( var i = 0; i < menus.length; i++ ){
// <div class="sidebar-header">一级菜单</div>
// <ul class="nav nav-sidebar">
// <li class="active">
// <a href="index.html#">
// 二级菜单 <span class="sr-only">(current)</span>
// </a>
// </li>
// <li><a href="index.html#">二级菜单</a></li>
// <li><a href="index.html#">二级菜单</a></li>
// <li><a href="index.html#">二级菜单</a></li>
// </ul>
				// 使用反引号是ECMAScript 5/6里面提供的新功能，可以直接写多行的字符串
				// $表示引用变量，后面跟着前面定义的变量名
				// ${menu.name}不能在JSP使用，只能在JS文件中使用
				var menu = menus[i];
				var html = `<div class="sidebar-header">${menu.name}</div>
				<ul class="nav nav-sidebar">`;
				// $("#left-sidebar").append(html);

// <li><a href="index.html#">二级菜单</a></li>
				for( var j = 0; j < menu.children.length; j ++ ){
					var item = `<li><a href="${contextPath}${menu.children[j].url}">${menu.children[j].name}</a></li>`;
					html += item;
				}

				html += "</ul>";
				if( menu.children.length > 0 ) {
					$(html).appendTo($("#left-sidebar"));
				}
			}
			
			// 高亮显示当前菜单
			// 1.获取当前的URL
			var url = document.location.pathname;
			//console.info(url);
			// 2.获取所有的菜单链接（显示出来的）
			var lis = $(".nav-sidebar li");
			// 排序
//			$(lis).each(function(){
//				console.info($("a", $(this)).attr("href"));
//			});
			lis.sort(function(li1, li2){
				// 长度优先，比较长的放前面
				return $("a", li2).attr("href").length - $("a", li1).attr("href").length ; 
			});
//			console.info("-----------------------");
//			$(lis).each(function(){
//				console.info($("a", $(this)).attr("href"));
//			});
			// 3.给匹配的URL的li增加class='active'
			for( var i = 0; i < lis.length; i++){
				var li = lis[i];
				var href = $("a", $(li)).attr("href");
				
				// href是左侧菜单，比较短，url可能还包含路径参数
				if( url.startsWith(href) ){
					$(li).addClass("active");
					break;
				}
			}
		},
		error: function(data, status, xhr){
			// 弹出错误信息
			alert(data.responseJSON.message);
		}
	});
});