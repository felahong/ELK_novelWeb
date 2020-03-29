<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>小说内容</title>
    <script src="js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="Bootstrap/css/bootstrap.css">
    <script src="Bootstrap/js/bootstrap.js"></script>
    <script src="js/common.js"></script>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/content.css">

</head>

<body>
<div id="header">
    <div class="head_cen">
        <div class="h_left">
            <img src="../img/felahong.jpg"/>
        </div>
        <div class="h_right">
            <c:if test="${empty user}">
                欢迎您
                <a href="login" alt="">登录</a>
            </c:if>
            <c:if test="${!empty user}">
                ${user.username}，你好
            </c:if>
        </div>
        <div class="h_search">
            <form action="#">
                <input type="text" placeholder="输入你想要的信息" class="t_input">
                <input type="submit" value="提交" class="t_button">
            </form>
        </div>
    </div>
</div>

<div class="block_div"></div>

<div class="c_left_top" onselectstart="return false">
    <%--<input type="hidden" id="location" value="${content.chapterUrl}">--%>
    <div class="chapter_title">
        <span>${content.chapterName}</span>
    </div>
    <div class="line_box">
        <div class="novel_name">
            <span>${content.novelName}</span>
        </div>
        <div class="novel_author">
            <span>${content.authorName}</span>
        </div>
        <div class="chapter_count">
            <span>字数：0</span>
        </div>
        <%--<div class="date"><span>更新时间：<fmt:formatDate value="${chapter.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>--%>
    </div>
</div>
<div class="center">
    <a href="${content.chapterUrl}" class="content" style="color: red;font-size: 20px">正文内容请点击我</a>
    <div class="comment">
        <form action="">
            <div class="coin_div">
                <p style="font-size: 20px">给作者一些鼓励吧~</p><br>

                <input class="coin" name="coin" type="radio" value="攒点情谊不容易，66硬币包养你" /><span>66币</span>
                <input class="coin" name="coin" type="radio" value="天青色等烟雨，而我在等你 99硬币催催你" /><span>99币</span>
                <input class="coin" name="coin" type="radio" value="召唤，召唤，666硬币砸来" /><span>666币</span>
                <input class="coin" name="coin" type="radio" value="问我爱你有多深，999硬币代表我的心" /><span>999币</span>
                <input class="coin" name="coin" type="radio" value="万水千山总是情，10000硬币行不行" /><span>10000币</span>

            </div>
            <img src="img/person-img/shahejiade.png "alt="" class="u_img">
            <textarea class="text" placeholder="说些什么吧..."></textarea>
            <input type="button" value="发布" class="send" disabled>
        </form>
        <div class="messageList">

        </div>
    </div>
</div>
<div class="c_right">
    <a href="openCatalog?nid=${content.id}"><span class="glyphicon glyphicon-list"></span></a>
    <a href="#"><span class="glyphicon glyphicon-wrench"></span></a>
    <%--<a href="javascript:;" onclick="changeChapter(${chapter.chapterid-1 })"><span class="glyphicon glyphicon-chevron-up"></span></a>--%>
    <%--<a href="javascript:;" onclick="changeChapter(${chapter.chapterid+1 })"><span class="glyphicon glyphicon-chevron-down"></span></a>--%>
    <a href="#"><span class="glyphicon glyphicon-tags"></span></a>
    <a href="#"><span class="glyphicon glyphicon-share-alt"></span></a>
    <a href="#"><span class="glyphicon glyphicon-heart-empty"></span></a>
</div>

<input type="hidden" id="nid" value="${content.id}">
<%--<script src="layer/layer.js"></script>--%>
<%--<script>--%>
<%--$(function () {--%>
<%--var str = "/brotherNovel/novel/"+$("#location").val();--%>
<%--var txt = $.ajax({--%>
<%--type: "get",--%>
<%--dataType: 'text',--%>
<%--url: str,--%>
<%--async: false--%>
<%--});--%>

<%--var Temp = txt.responseText;--%>
<%--addAticle(Temp);--%>
<%--chapter_count(Temp);--%>
<%--//0.监听内容的实时输入--%>
<%--$("body").delegate(".text","propertychange input", function () {--%>
<%--//判断是否输入了内容--%>
<%--if($(this).val().length > 0){--%>
<%--console.log($(this).val());--%>
<%--//让按钮可用--%>
<%--$(".send").prop("disabled", false);--%>
<%--}else{--%>
<%--//让按钮不可用--%>
<%--$(".send").prop("disabled", true);--%>
<%--}--%>
<%--});--%>

<%--//1.监听发布按钮的点击--%>
<%--$(".send").click(function () {--%>
<%--//拿到用户输入的内容--%>
<%--var $text = $(".text").val();--%>
<%--//根据内容创建节点--%>
<%--var $commant = createCom($text);--%>
<%--//插入微博--%>
<%--$(".messageList").prepend($commant);--%>
<%--$(".text").val("");--%>
<%--var $radio = $(':radio');--%>
<%--$.each($radio, function (index, value) {--%>
<%--if ($(value).data('waschecked') == true) {--%>
<%--$(value).prop('checked', false);--%>
<%--$(value).data('waschecked', false);--%>
<%--$(".send").prop("disabled", true);--%>
<%--}--%>
<%--});--%>
<%--});--%>

<%--$(':radio').click(function(){--%>
<%--var $radio = $(this);--%>
<%--if ($radio.data('waschecked') == true){--%>
<%--$radio.prop('checked', false);--%>
<%--$radio.data('waschecked', false);--%>
<%--$(".send").prop("disabled", true);--%>
<%--$(".text").val("");--%>
<%--} else {--%>
<%--$radio.prop('checked', true);--%>
<%--$radio.data('waschecked', true);--%>
<%--$(".text").val($(this).val());--%>
<%--$(".send").prop("disabled", false);--%>
<%--}--%>
<%--});--%>
<%--});--%>
<%--function createCom(text) {--%>
<%--var $commant = $("<div class=\"info\">\n" +--%>
<%--"                <div class=\"info_img\">\n" +--%>
<%--"                    <img src=\"img/person-img/shahejiade.png\" alt=\"\">\n" +--%>
<%--"                </div>\n" +--%>
<%--"                <div class=\"info_content\">\n" +--%>
<%--"                    <span class=\"info_tour\">西芙酱</span>\n" +--%>
<%--"                    <span class=\"info_time\">"+formatDate()+"</span>\n" +--%>
<%--"                    <p class=\"info_page\">"+text+"</p>\n" +--%>
<%--"                    <span class=\"info_count\">第<span class=\"count\">0</span>层</span>\n" +--%>
<%--"                </div>\n" +--%>
<%--"            </div>");--%>
<%--return $commant;--%>
<%--}--%>
<%--//生成时间方法--%>
<%--function formatDate() {--%>
<%--var date = new Date();--%>
<%--var arr = [date.getFullYear()+"-",--%>
<%--date.getMonth()+1+"-",--%>
<%--date.getDate()+" ",--%>
<%--date.getHours()+":",--%>
<%--date.getMinutes()+":",--%>
<%--date.getSeconds()];--%>
<%--// console.log(arr.join(""));--%>
<%--return arr.join("");--%>
<%--}--%>
<%--function addAticle(str) {--%>

<%--var array = str.split("\n");--%>
<%--$.each(array, function (index, value) {--%>
<%--$(".content").append("<p>" + value.replace(/(^\s*)/g," ") + "</p>");--%>
<%--});--%>
<%--};--%>
<%--function chapter_count(str) {--%>
<%--$(".chapter_count>span").text("字数：" + str.length);--%>
<%--}--%>
<%--//上一章 / 下一章--%>
<%--function changeChapter(chapterid) {--%>

<%--var nid= $("#nid").val();--%>
<%--$.ajax({--%>
<%--type:"POST",--%>
<%--url:"judgeCatalog",--%>
<%--data:{"nid":nid,"chapterid":chapterid},--%>
<%--async:true,--%>
<%--success:function(data) {--%>
<%--console.log(data);--%>
<%--if(data == "noSmaller"){--%>
<%--layer.alert("这已经是第一章了");--%>
<%--}else if(data == "noBigger"){--%>
<%--layer.alert("这已经是最后一章了");--%>
<%--}else if(data =="turn"){--%>
<%--window.location.replace("content?nid="+nid+"&chapterid="+chapterid);--%>
<%--}--%>
<%--},--%>
<%--});--%>
<%--}--%>

<%--</script>--%>

</body>

</html>