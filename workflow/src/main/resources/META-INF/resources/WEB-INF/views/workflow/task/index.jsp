<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 引用JSP Tag文件 --%>
<%@ taglib prefix="fk" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>待办任务</title>
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			待办任务
		</div>
		<div class="panel-body">
			<!-- Table -->
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>流程名称</th>
						<th>流程实例创建时间</th>
						<th>流程实例创始人</th>
						<th>任务名称</th>
						<th>任务创建时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content }" var="u">
					<%-- 点击tr的时候打开任务详情 --%>
					<tr onclick="openTask('<c:url value="/workflow/task/${u.task.id }"/>')" style="cursor: pointer;">
						<td>${u.definition.name }</td>
						<td><fmt:formatDate value="${u.instance.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${u.initialUser.name }</td>
						<td>${u.task.name }</td>
						<td><fmt:formatDate value="${u.task.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5" style="text-align: center;">
							<fk:page url="/workflow/task?keyword=${param.keyword }" page="${page }"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
function openTask( url ){
	document.location.href = url;
}
</script>
</body>
</html>