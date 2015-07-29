<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<%  
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + path+"/";  
%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="images/tool.ico">
<link rel="icon" href="images/tool.ico" type="image/x-icon" />
<link rel="Bookmark" href="images/tool.ico" type="image/x-icon" />
<link rel="shortcut icon" href="images/tool.ico" type="image/x-icon" />
<script type="text/javascript">
	function submitForm(formId){
		document.forms[formId].submit();
	}

</script>

<title>商户四审</title>
</head>
<body>
	<table id="main" border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
		<tr style="text-align: left; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td colspan="4">
				<form id="more" name="more" action="validate/findByMobile.action" method="post">
					<c:out value="输入手机号:"></c:out><input type="text" name="mobile" value="${mobile}"/><input type="submit" name="查询" value="查询" />
				</form>
			</td>
		</tr>
		<c:if test="${message != null }">
		<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td colspan="4"><c:out value="${message }"></c:out></td>
		</tr>
		</c:if>
		<c:if test="${merchants != null }">
		<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td>
				<c:out value="手机号码"></c:out>
			</td>
			<td>
				<c:out value="实名认证状态"></c:out>/
				<c:out value="商户认证状态"></c:out>
			</td>
			<td>
				<c:out value="账户认证状态"></c:out>/
				<c:out value="签名认证状态"></c:out>
			</td>
			<td>
				<c:out value="操作"></c:out>
			</td>
		</tr>
		<c:forEach items="${merchants }" var="merchant">
		<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td>
				<c:out value="${merchant['mobile'] }"></c:out>
			</td>
			<td>
				<c:out value="${merchant['idCard'] }"></c:out>/
				<c:out value="${merchant['merchant'] }"></c:out>
			</td>
			<td>
				<c:out value="${merchant['account'] }"></c:out>/
				<c:out value="${merchant['signature'] }"></c:out>
			</td>
			<td>
				<form action="validate/pass.action" method="post">
					<input type="hidden" name="mobile" value="${merchant['mobile'] }"/>
					<input type="submit" name="passAll" value="一键通过" />/
					<input type="submit" name="unpassAll" value="初始化状态" />
				</form>
			</td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
</body>
</html>