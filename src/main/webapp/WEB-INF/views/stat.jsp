<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page session="false"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>供水管理系统</title>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="<c:url value="/resources/form.css" />" rel="stylesheet"
	type="text/css" />
<link
	href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.all.css" />"
	rel="stylesheet" type="text/css" />

<style>
body {
	width: 100%;
}

div.wrapper {
	width: 100%;
	text-align: center;
}

.stationInfo {
	border: 2px solid black;
	display: inline-block;
}
</style>
</head>
<body>
	<div class="wrapper">

		<c:forEach items="${stations}" var="station" varStatus="status">
			<div class="stationInfo">
				<table>
					<tr>
						<td>站点号</td>
						<td><c:out value="${station.value.stationId}" /></td>
					</tr>
					<tr>
						<td>网络地址</td>
						<td><c:out value="${station.value.lastDataFrame.ip}" />:<c:out
								value="${station.value.lastDataFrame.port}" /></td>
					</tr>
					<tr>
						<td>电压</td>
						<td><c:out value="${station.value.lastDataFrame.voltage}" /></td>
					</tr>
					<tr>
						<td>电流</td>
						<td><c:out value="${station.value.lastDataFrame.electricity}" /></td>
					</tr>
					<tr>
						<td>水压</td>
						<td><c:out value="${station.value.lastDataFrame.waterGage}" /></td>
					</tr>
					<tr>
						<td>水位</td>
						<td><c:out value="${station.value.lastDataFrame.waterLevel}" /></td>
					</tr>
					<tr>
						<td>更新时间</td>
						<td><fmt:formatDate value="${station.value.lastDataFrame.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					<tr>
						<td>主泵</td>
						<td><input type="button" value="开启" /> <input type="button"
							value="关闭" /></td>
					</tr>
					<tr>
						<td>辅泵</td>
						<td><input type="button" value="开启" /> <input type="button"
							value="关闭" /></td>
					</tr>
				</table>
			</div>
		</c:forEach>
	</div>
</body>
</html>