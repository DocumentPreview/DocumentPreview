<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@ page import="java.io.File"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="org.apache.commons.io.IOUtils"%>
<%@ page import="org.mozilla.universalchardet.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	pageContext.setAttribute("isImage", request.getParameter("isImage"));
	pageContext.setAttribute("file", request.getParameter("file"));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html dir="ltr">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="google" content="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Image viewer</title>

<style type="text/css">
#viewer {
	margin: 0 auto;
	width: 90%;
	max-width: 793px
}
</style>

<!-- This snippet is used in production (included from viewer.html) -->


</head>

<body style="background-color: #404040;">
	<div id="outerContainer">

		<div id="mainContainer">

			<div id="viewerContainer" style="outline: none;">
				<div id="viewer" class="pdfViewer">
					<%--<c:if test="${isImage }">--%>
						<div  align="center">
							<img src="${file }" style="max-width: 100%">
						</div>
					<%--</c:if>--%>
					<c:if test="${isText }">
						<div style="background:white;min-height:600px;padding: 3%;margin-bottom: 2%; max-width: 100%">
							<pre>${content }</pre>
						</div>
					</c:if>
					<c:if test="${notSupport }">
						<div  align="center" style="background:white;padding:30%; max-width: 100%">
							<span>该文件暂不支持预览</span>
						</div>
					</c:if>
				</div>
			</div>

		</div>

	</div>
	<!-- outerContainer -->

</body>
</html>
