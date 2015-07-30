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

<title>卡号信息查询</title>
</head>
<body>
	<table id="main" border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
		<tr style="text-align: left; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td colspan="6">
				<form id="more" name="more" action="bankcard/scanBankCard.action" method="post">
					<c:out value="输入卡号:"></c:out><input type="text" name="cardno" value="${cardno}"/><input type="submit" name="查询" value="查询" />
				</form>
			</td>
		</tr>
		<c:if test="${message != null }">
		<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td colspan="6"><c:out value="${message }"></c:out></td>
		</tr>
		</c:if>
		<c:if test="${bankCards != null }">
		<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td>
				<c:out value="卡号"></c:out>
			</td>
			<td>
				<c:out value="长度"></c:out>
			</td>
			<td>
				<c:out value="卡号编码"></c:out>
			</td>
			<td>
				<c:out value="卡号类型"></c:out>
			</td>
			<td>
				<c:out value="卡名称"></c:out>
			</td>
			<td>
				<c:out value="发卡行及编号"></c:out>
			</td>
		</tr>
		<c:forEach items="${bankCards }" var="bankCard">
		<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td>
				<c:out value="${bankCard['cardNo'] }"></c:out>
			</td>
			<td>
				<c:out value="${bankCard['cardLength'] }"></c:out>
			</td>
			<td>
				<c:out value="${bankCard['cardCode'] }"></c:out>
			</td>
			<td>
				<c:out value="${bankCard['cardType'] }"></c:out>
			</td>
			<td>
				<c:out value="${bankCard['cardName'] }"></c:out>
			</td>
			<td>
				<c:out value="${bankCard['cardIssuer'] }"></c:out>
			</td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
</body>
</html>