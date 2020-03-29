<%--
  Created by IntelliJ IDEA.
  User: 22969
  Date: 2019/1/7
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8"%>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" type="text/css" href="../css/common.css" />
    <link rel="stylesheet" type="text/css" href="../css/header2.css"/>
    <script src="../js/jquery-1.11.3.min.js"></script>
    <script src="../js/common.js"></script>
</head>
<body>
<div id="header">
    <div class="h_left">
        <img src="../img/felahong.jpg" />
    </div>
    <div class="h_center">
        <ul>
            <li>
                <a href="index">首页</a>
            </li>
            <li>
                <a href="#">更新</a>
            </li>
            <li>
                <a href="#">排行</a>
            </li>
            <li>
                <a href="#">专题</a>
            </li>
        </ul>
    </div>
    <div class="h_right">
        ${user.username}
        <a href="javascript:;" type="button" style="margin-left: 10px; color: #666666" class="logout">退出</a>
    </div>
</div>
</body>
</html>
