<%@ page isELIgnored="false" %>
<%@ page import="com.sif.pojo.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${pageContext.request.contextPath}/">
	<meta charset="UTF-8">
	<title>小说专区</title>
	<script src="js/jquery-1.11.3.min.js"></script>
	<link rel="stylesheet" href="Bootstrap/css/bootstrap.css">
    <script src="Bootstrap/js/bootstrap.js"></script>
	<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
	<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css">
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<link rel="stylesheet" type="text/css" href="css/novel.css" />
</head>

<body>
	<!--头部-->
	<%@ include file="head.jsp"%>

	<iframe src="main" name="iframe_a" width="100%" onload="setIframeHeight(this)" frameborder="0" scrolling="no"></iframe>

	<%@ include file="footer.jsp"%>

	<script>
        function setIframeHeight(iframe) {
            if (iframe) {
                var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
                if (iframeWin.document.body) {
                    iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
                }
            }
        };

        window.onload = function () {
            setIframeHeight(document.getElementById('external-frame'));
        };
	</script>
</body>
</html>