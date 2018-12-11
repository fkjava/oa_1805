<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 引用JSP Tag文件 --%>
<%@ taglib prefix="fk" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>处理任务</title>
<link rel="stylesheet" href="<c:url value='/css/workflow.css'/>"/>
<link rel="stylesheet" href="${ctx }/static/datetimepicker/css/bootstrap-datetimepicker.min.css"/>
</head>
<body>
<script type="text/javascript">
var businessData = ${json};
</script>
<div class="container-fluid">
	<c:if test="${not empty result }">
		<div class="alert alert-danger" role="alert">${result.message }</div>
	</c:if>
	<form action="./${form.task.id }" 
		method="post" 
		enctype="multipart/form-data"
		onsubmit="return checkFormOnSubmit();">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				处理【${form.definition.name }】流程的【${form.task.name }】任务
			</div>
			<div class="panel-body">
				<c:if test="${not empty form.task.description }">
					<div class="alert alert-info" role="alert">${form.task.description }</div>
				</c:if>
				<p>
				<%-- 业务私有的部分开始 --%>
				${form.content }
				<%-- 业务私有的部分结束 --%>
				</p>
				<fieldset class="col-sm-12 col-md-6">
					<legend>备注</legend>
					<textarea name="remark" class="form-control" rows="10" required="required"></textarea>
				</fieldset>
				<%-- 循环生成所有的表单属性 --%>
				<c:forEach items="${form.formData.formProperties }" var="p">
				<fieldset class="col-sm-12 col-md-6">
					<%-- 表单属性的【Name】 --%>
					<legend>${p.name }</legend>
					<%-- 如果是枚举，继续循环键值对，生成单选框，name属性的值是表单属性的【Id】 --%>
					<c:if test="${p.type.name eq 'enum' }">
						<c:forEach items="${p.type.getInformation('values') }" var="entry">
							<label>${entry.key }<input name="${p.id }" value="${entry.value }" type="radio"/></label>
						</c:forEach>
					</c:if>
				</fieldset>
				</c:forEach>
			</div>
			<div class="panel-footer text-right">
				<button type="submit" class="btn btn-primary">提交</button>
			</div>
		</div>
		<input type="hidden"
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
	</form>
</div>
<script type="text/javascript" src="<c:url value='/static/datetimepicker/js/bootstrap-datetimepicker.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/static/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/workflow.js'/>"></script>
</body>
</html>