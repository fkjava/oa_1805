<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑公告</title>
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			编辑公告
		</div>
		<div class="panel-body">
			<div id="noticeContentEditor">
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx }/webjars/wangEditor/3.1.1/release/wangEditor.min.js"></script>
<script type="text/javascript">
    var E = window.wangEditor;
    var editor = new E('#noticeContentEditor');
    // 或者 var editor = new E( document.getElementById('editor') );
    
    // 隐藏网络图片引用tab
    //editor.customConfig.showLinkImg = false;
    
    // 显示图片上传的tab
    editor.customConfig.uploadImgServer = '${ctx}/storage/file/wangEditor';
    // 上传的时候，文件的字段名
    editor.customConfig.uploadFileName = 'file';
    // 自定义上传的时候请求头内容
    editor.customConfig.uploadImgHeaders = {
   	    '${_csrf.headerName}': '${_csrf.token}'
   	};
    
    // 创建编辑器
    editor.create();
</script>
</body>
</html>