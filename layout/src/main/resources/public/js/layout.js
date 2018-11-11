$(document).ready(function() {
	// 显示或者隐藏侧边栏
	$('[data-toggle="sidebar"]').click(function() {
		$('.sidebar').toggleClass('active')
	});
});