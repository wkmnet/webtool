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

<title>解绑KSN/手机号</title>
</head>
<body>
	<table id="main" border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
		<tr style="text-align: left; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td colspan="4">
				<form id="more" name="more" action="app/map.action" method="post">
				<select name="mapItem" onchange="javascript:submitForm('more')">
					<option selected="selected" value="">更多...</option>
					<option value="messageValidateCode">获取短信验证码</option>
					<option value="messageFactValidateCode">获取生产环境短信验证码</option>
				</select>
				</form>
			</td>
		</tr>
		<tr style="text-align: left; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td colspan="4">
				<table id="main" border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
				<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
					<td>
						<a href="<%=basePath%>validate/index.action" target="_blank">商户四审</a>
					</td>
					<td>
						<a href="<%=basePath%>bankcard/index.action" target="_blank">银行卡信息查询</a>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<c:if test="${message != null }">
		<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td colspan="4"><c:out value="${message }"></c:out></td>
		</tr>
		</c:if>
		<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td colspan="1">
				<form action="app/scanKsn.action" method="post">
					<c:out value="KSN:"></c:out><input type="text" name="ksnNo" value="${ksnNo}"/><input type="submit" name="查询" value="查询" />
				</form>
			</td>
			<td colspan="1" align="center">
					<form action="app/unbundMobileNo.action" method="post">
					<c:out value="手机号:"></c:out><input type="text" name="mobileNo" value="${mobileNo}"/><input type="submit" name="解绑" value="解绑" />
				</form>
			</td>
			<td colspan="1" align="center">
					<form action="app/deleteMobileMessage.action" method="post">
					<c:out value="手机号:"></c:out><input type="text" name="mobileMessage" value="${mobileMessage}"/><input type="submit" name="清除短信" value="清除短信" />
				</form>
			</td>
			<td colspan="1">
				<form action="app/scanAccount.action" method="post">
					<c:out value="卡号:"></c:out><input type="text" name="accountNo" value="${accountNo}"/><input type="submit" name="查询" value="查询" />
				</form>
			</td>
		</tr>
		<c:if test="${ksns != null }">
		<c:forEach items="${ksns }" var="ksn">
		<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td>
				<c:out value="${ksn['ksnNo'] }"></c:out>
			</td>
			<td>
				<c:out value="${ksn['isActivated'] }"></c:out>
			</td>
			<td>
				<c:out value="${ksn['isUsed'] }"></c:out>
			</td>
			<td>
				<form action="app/unbundKsnNo.action" method="post">
					<input type="hidden" name="ksnNo" value="${ksnNo}"/>
					<input type="hidden" name="unbundKsnNo" value="${ksn['ksnNo'] }"/>
					<input type="submit" name="unbundButton" value="解绑" />
					<input type="submit" name="clearButton" value="清除短信" />
					<input type="submit" name="sendIcKey" value="发送IC公钥" />
					<input type="submit" name="resetTMK" value="重置主密钥TMK" />
				</form>
			</td>
		</tr>
		</c:forEach>
		</c:if>
		<c:if test="${accountList != null }">
		<c:forEach items="${accountList }" var="account">
		<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<td>
				<c:out value="${account['accountNo'] }"></c:out>-
				<c:out value="${account['accountName'] }"></c:out>
			</td>
			<td>
				<c:out value="${account['bankName'] }"></c:out>
			</td>
			<td>
				<c:out value="${account['isVerified'] }"></c:out>
			</td>
			<td>
				<form action="app/unbundAccount.action" method="post">
					<input type="hidden" name="accountNo" value="${account['accountNo'] }"/>
					<c:if test="${forceDeleteAccount == null }">
					<input type="submit" name="解绑" value="解绑" />
					</c:if>
					<c:if test="${forceDeleteAccount != null }">
					<input type="hidden" name="forceDeleteAccount" value="true"/>
					<input type="submit" name="强制解绑" value="强制解绑" />
					</c:if>
				</form>
			</td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
</body>
</html>