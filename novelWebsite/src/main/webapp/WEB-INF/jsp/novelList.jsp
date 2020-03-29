<%--
  Created by IntelliJ IDEA.
  User: 22969
  Date: 2019/1/6
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>小说类别</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/header.css">
    <style>
        #center{
            width: 80%;
            margin: 0 auto;
        }
        #center .c_content{
            margin-top: 10px;
        }
        .p_title{
            width: 100%;
            height: 30px;
            display: block;
        }
        .p_title p{
            line-height: 30px;
            font-size: 17px;
            font-weight: bold;
            color: #2f3238;
        }
        p a{
            color: #2aabd2;
        }
        .card-body{
            width: 100%;
            border: 1px solid #ddd;
        }
        .card-body .table-head{
            border-bottom: 1px solid #ddd;
        }
        .table-head .pic{
            width: 10%;
        }
        .table-head .name{
            width: 10%;
        }
        .table-head .author{
            width: 10%;
        }
        .table-head .type{
            width: 10%;
        }
        .table-head .desc{
            width: 23%;
        }
        .table-head .chapter{
            width: 10%;
        }
        .table-head .time{
            width: 15%;
        }
        .card-body .table-head th {
            height: 30px;
            font-weight: normal;
            font-size: 16px;
            text-align: center;
            line-height: 30px;
            background: #f9f9f9 0 bottom repeat-x;
            border-right: 1px solid #ddd;
        }
        .card-body .table-body td {
            padding: 5px;
            border-bottom: 1px solid #ddd;
            border-right: 1px solid #e1e1e1;
            text-align: center;
        }
        .card-body .table-body tr {
            line-height: 35px;

        }
        .card-body img {
            width: 70px;
            height: 100px;
        }
        .result-content .result-tab tr:nth-child(odd) {
            background: #f9f9f9;
        }
    </style>
    <script src="js/jquery-1.11.3.min.js"></script>
</head>
<body>
<div id="center">
    <div class="c_content">
        <div class ="p_title">
            <p>
                小说>>搜索>><a>${queryString}</a>
            </p>
        </div>
        <table class="card-body">
            <tr class="table-head">
                <th class="pic">封面</th>
                <th class="name">书名</th>
                <th class="author">作者</th>
                <th class="type">小说类型</th>
                <th class="desc">简介</th>
                <th class="chapter">章节</th>
                <th class="time">更新时间</th>
            </tr>
            <c:forEach items="${searchNovel.novelList}" var="novel" varStatus="status">
                <tr class="table-body" onclick="openCatalog('${novel.id}')">
                    <td><img src="${novel.picUrl}" /></td>
                    <td>${novel.name}</td>
                    <td>${novel.author}</td>
                    <td>${novel.category}</td>
                    <td>${novel.novelInfo}</td>
                    <c:if test="${novel.lastChapter eq null}">
                        <td>暂无章节</td>
                        <td>/</td>
                    </c:if>
                    <c:if test="${novel.lastChapter ne null}">
                        <td>${novel.lastChapter}</td>
                        <td>${novel.lastupdate}</td>
                    </c:if>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $(".table-body").hover(function(){
            $(this).css("background","#f1f1f1");
        },function (){
            $(this).css("background","none");
        });
    });
    function openCatalog(nid) {
        console.log(1111);
        window.location.replace("openCatalog?nid="+nid);
    }

</script>

</body>
</html>