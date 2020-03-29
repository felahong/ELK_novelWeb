<%--
  Created by IntelliJ IDEA.
  User: 22969
  Date: 2019/1/2
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8"%>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>作家中心</title>
    <script src="js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="Bootstrap/css/bootstrap.css">
    <script src="Bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/person-admin.css" />
    <link rel="stylesheet" type="text/css" href="css/person-index.css" />
</head>
<body>
<%@ include file="head2.jsp"%>
<div id="center">
    <div class="c_left">
        <ul class="nav nav-pills nav-stacked">
            <li>
                <a href="authorMain" target="iframe_c"><span class="glyphicon glyphicon-home" style="font-size: 15px;"></span>&nbsp;&nbsp;作者首页</a>
            </li>
            <li>
                <a href="novelManage" target="iframe_c"><span class="glyphicon glyphicon-book" style="font-size: 15px;"></span>&nbsp;&nbsp;小说管理</a>
            </li>
            <li>
                <a href="#"><span class="glyphicon glyphicon-usd" style="font-size: 15px;"></span>&nbsp;&nbsp;收入管理</a>
            </li>
            <li>
                <a href="#"><span class="glyphicon glyphicon-list-alt" style="font-size: 15px;"></span>&nbsp;&nbsp;基本信息</a>
            </li>
            <li>
                <a href="#"><span class="	glyphicon glyphicon-glass" style="font-size: 15px;"></span>&nbsp;&nbsp;作家福利</a>
            </li>
        </ul>
    </div>
    <iframe src="authorMain" name="iframe_c" width="78%" onload="setIframeHeight(this)" frameborder="0" scrolling="no"></iframe>
</div>
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
