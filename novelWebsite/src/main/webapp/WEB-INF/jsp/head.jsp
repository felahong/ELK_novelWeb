<%--
  Created by IntelliJ IDEA.
  User: 22969
  Date: 2019/1/7
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8"%>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <script src="js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/header.css" />
    <script src="js/common.js"></script>
    <title>Title</title>
    <script src="js/head.jsp"></script>
</head>
<body>
<div id="header">

    <div class="h_top">
        <div class="low">
            <div class="list">
                <a href="index" title="首页">首页</a> <a href="#" title="小说专区">小说专区</a> <a
                    href="#" title="视频专区">视频专区</a> <a href="#" title="同人专区">同人专区</a> <a
                    href="#" title="周边区">周边区</a> <a href="#" title="讨论栏">讨论栏</a>
            </div>
            <div class="login">
                <c:if test="${empty user}">
                    欢迎您
                    <a href="login" alt="">登录</a>
                </c:if>
                <c:if test="${!empty user}">
                    欢迎：${user.username}
                    <a href="javascript:;" type="button" style="margin-left: 10px; color: #666666" class="logout">退出登录</a>
                    <a href="personIndex" target="_blank"
                       style="margin-left: 10px; color: #666666">个人中心</a>
                </c:if>
            </div>
        </div>
    </div>
    <div class="h_center">
        <div class="word">
            <img src="../img/felahong.jpg" alt="">
        </div>
        <div class="search">
            <form class="search-inline" action="solr/novelList" method="get" target="iframe_a">
                <input type="text" placeholder="输入你想要的信息" class="t_input" name="queryString">
                <input type="submit" value="提交" class="t_button" id="search">
            </form>
            <div class="hot">
                <p>最新话题</p>
                <a href="#">终极斗罗</a> <a href="#">择天记</a> <a href="#">妖神记</a> <a href="#">星辰变</a>
            </div>
        </div>
    </div>
    <div class="h_bottom">
        <div class="c_top">
            <div class="top_list">
                <ul>
                    <li>
                        <a href="index" class="sign">首页</a>
                    </li>
                    <li>
                        <a href="#" class="sign">更新</a>
                    </li>
                    <li>
                        <a href="#" class="sign">排行</a>
                    </li>
                    <li>
                        <a href="#" class="sign">专题</a>
                    </li>
                </ul>
            </div>
            <div class="novel_search">
                <input type="text" placeholder="输入关键字" class="t_input">
                <input type="button" class="t_button" >
            </div>
        </div>
        <div class="c_topic">
            <!--话题-->
            <ul>
                <li>
                    <a href="solr/novelList?queryString=都市言情" target="iframe_a" class="sign" >都市言情</a>
                </li>
                <li>
                    <a href="solr/novelList?queryString=武侠修真" target="iframe_a" class="sign" >武侠修真</a>
                </li>
                <li>
                    <a href="solr/novelList?queryString=网游竞技" target="iframe_a" class="sign" >网游竞技</a>
                </li>
                <li>
                    <a href="solr/novelList?queryString=武侠仙侠" target="iframe_a" class="sign" >武侠仙侠</a>
                </li>
                <li>
                    <a href="solr/novelList?queryString=历史军事" target="iframe_a" class="sign" >历史军事</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<script src="layer/layer.js"></script>
<script type="text/javascript">
    $(function () {

        $("body").keydown(function() {
            if (event.keyCode == "13") {//keyCode=13是回车键
                $('#search').click();
            }
        });
        //1.给button设置点击事件
       $("#search").click(function () {
           var queryString = $('input[name="queryString"]').val();
           if(queryString == ''){
               layer.alert("输入不能为空");
               return;
           }});
    });
</script>
</body>
</html>
