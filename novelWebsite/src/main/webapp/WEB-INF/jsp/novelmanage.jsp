<%--
  Created by IntelliJ IDEA.
  User: 22969
  Date: 2019/1/2
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>小说管理</title>
    <script src="js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="Bootstrap/css/bootstrap.css">
    <script src="Bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/person-admin.css" />
    <style type="text/css">
        .c_right {
            width: 100%;
        }
        .c_right .c_r_create {
            display: block;
            width: 100%;
            height: 40px;
        }

        .c_right .c_r_create a {
            float: right;
            margin-right: 20px;
            font-size: 16px;
            color: white;
            border: 1px burlywood solid;
            background: salmon;
        }

        .result-content {
            margin-top: 10px;
            width: 100%;
        }

        .result-content .result-tab th {
            font-weight: normal;
            font-size: 15px;
            text-align: center;
            background: #f9f9f9 0 bottom repeat-x;
        }

        .result-content .result-tab td {
            text-align: center;
            border-bottom: 1px solid #ddd;
            border-right: 1px solid #e1e1e1;
        }

        .result-content .result-tab tr {
            line-height: 35px;
        }

        .result-content .result-tab img {
            width: 70px;
            height: 100px;
        }

        .result-content .result-tab tr:nth-child(odd) {
            background: #f9f9f9;
        }

        .result-content .result-tab tr:hover {
            background: #f1f1f1;
        }

        .result-content .result-tab tr.even-tr {
            background: #e9fff2;
        }
    </style>
</head>
<body>
<div class="c_right">
        <form name="myform" id="myform" method="post">
            <div class="c_r_create">
                <a href="createNovel" target="iframe_c">新增作品</a>
            </div>
            <div class="result-content">
                <table class="result-tab" width="100%">
                    <tr>
                        <th>封面</th>
                        <th>书名</th>
                        <th>分类</th>
                        <th>最新章节</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${novels}" var = "novel" varStatus="status">
                        <tr>
                            <td><img src="${novel.picUrl}" /></td>
                            <td>${novel.name }</td>

                            <%--<c:forEach items="${types}" var = "type" varStatus="status">--%>
                                    <%--<c:if test="${type.tid eq novel.nclass}">--%>
                                        <td>${novel.category}</td>
                                        <%--<c:set var="exitId" value="0"></c:set>--%>
                                    <%--</c:if>--%>
                            <%--</c:forEach>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${novel.lastchapter eq 0}"><!-- 如果 -->--%>
                                <%--<td>暂无章节</td>--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${novel.lastchapter ne 0}"><!-- 如果 -->--%>

                            <c:choose>

                                <c:when test="${novel.lastChapter == '暂无章节'}">    <!--如果 -->
                                    <td>暂无章节</td>
                                </c:when>

                                <c:otherwise>  <!--否则 -->
                                    <td>第  ${novel.lastChapter}  章</td>
                                </c:otherwise>

                            </c:choose>

                            <%--<td>第${novel.lastChapter}章</td>--%>

                                <%--</c:when>--%>
                            <%--</c:choose>--%>

                            <%--创建时间--%>
                            <td>${novel.lastupdate}</td>

                            <td><a href="insertChapter?nid=${novel.id}&lastchapter=${novel.lastChapter}"
                                   target="iframe_c" class="link-update" >更新章节</a>

                                <!-- 如果要删除作品，到数据库中把内容删除 -->

                                <a href="javascript:;" onclick="deleteNovel('${novel.id}')">删除</a>
                                <%--<button onclick="deleteNovel(${novel.nid})">删除</button>--%>
                            </td>
                        </tr>
                    </c:forEach>

                </table>

            </div>
        </form>
    </div>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="layer/layer.js"></script>
<script type="text/javascript">
    function deleteNovel(nid) {
        if(confirm('你确定要删除小说吗?')) {
            $.post("deleteNovel",
                {"nid":nid},
                function(data){
                layer.alert("删除小说成功！");
                window.location.reload();
            });
        }
    }
</script>
</body>
</html>
