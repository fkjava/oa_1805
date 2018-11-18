<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fk" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>公告管理</title>
<style type="text/css">
.unread{
	font-weight: bold;
}
</style>
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			公告管理
			<div class="close">
				<a class="btn btn-default" href="${ctx }/notice/add">新增</a>
			</div>
		</div>
		<div class="panel-body">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>标题</th>
						<th>作者</th>
						<th>撰写时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.content }" var="nr">
						<tr class="${(nr.notice.status eq 'RELEASED' and empty nr.readTime) ? 'unread': '' }">
							<td>${nr.notice.title }</td>
							<td>${nr.notice.author.name }</td>
							<td><fmt:formatDate value="${nr.notice.writeTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>
								<c:choose>
									<c:when test="${nr.notice.status eq 'DRAFT' }">
										草稿
									</c:when>
									<c:when test="${nr.notice.status eq 'RECALL' }">撤回</c:when>
									<c:when test="${nr.notice.status eq 'RELEASED' }">已发布</c:when>
								</c:choose>
							</td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5" style="text-align: center;">
							<fk:page url="/notice?keyword=${param.keyword }" page="${page }"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>
</body>
</html>