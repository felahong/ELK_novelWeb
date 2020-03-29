<%--
  Created by IntelliJ IDEA.
  User: 22969
  Date: 2019/1/8
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="Bootstrap/css/bootstrap.css">
    <script src="Bootstrap/js/bootstrap.js"></script>
    <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
    <link rel="stylesheet" href="css/jquery.mCustomScrollbar.css">
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/novel.css"/>
</head>
<body>
<!--中部-->
<div id="novel_center">

    <div class="row top_content">
        <div class="col-sm-3 ancient_rank">
            <!--左边-->
            <div class="t_l_box ">
                <h3 class="novel_type">都市言情</h3>
                <ul class="ul_list n_box">
                    <%
                        int i = 0;
                    %>
                    <c:forEach items="${allNovel}" var="novel" varStatus="status">
                        <c:if test="<%=i<10%>">
                            <c:if test="${novel.category eq '都市言情'}">
                                <%
                                    i = i + 1;
                                %>
                                <li class="card-item">
                                    <span class="digital_rank "><%=i%></span>
                                    <a href="openCatalog?nid=${novel.id}" class="novel_name">${novel.name}</a>
                                    <a href="solr/openAuthor?nauthor=${novel.author}" class="author_name" target="iframe_a">${novel.author}</a>
                                </li>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </ul>
                <a class="more" href="solr/novelList?queryString=都市言情">MORE</a>
            </div>
        </div>
        <div class="t_centent col-sm-6">
            <!--中间-->
            <div class="t_hot_novel row col-sm-12">
                <div class="b_top_box">
                    <h3 class="novel_type">最近更新</h3>
                    <a class="more" href="# ">MORE</a>
                </div>
                <div class="ul_list">
                    <ul>
                        <% i = 0; %>
                        <c:forEach items="${lastedNovel}" var="lastnovel" varStatus="status">
                            <%
                                i = i + 1;
                            %>
                            <li>
                                <a href="openCatalog?nid=${lastnovel.id}" class="novel_name">[${lastnovel.category}] ${lastnovel.name}</a>
                                <a href="#" class="author_name">${lastnovel.author}</a>
                                <p>${lastnovel.lastupdate}</p>
                            </li>
                        </c:forEach>

                    </ul>
                </div>
            </div>
        </div>
        <div class="morden_rank col-sm-3">
            <!--右边-->
            <div class="t_r_box">
                <h3 class="novel_type ">武侠修真</h3>
                <ul class="ul_list n_box">
                    <%
                        i = 0;
                    %>
                    <c:forEach items="${allNovel}" var="novel" varStatus="status">
                        <c:if test="<%=i<10%>">
                            <c:if test="${novel.category eq '武侠修真'}">
                                <%
                                    i = i + 1;
                                %>
                                <li class="card-item">
                                    <span class="digital_rank "><%=i%></span>
                                    <a href="openCatalog?nid=${novel.id}" class="novel_name ">${novel.name}</a>
                                    <a href="solr/openAuthor?nauthor=${novel.author}" class="author_name" target="iframe_a">${novel.author}</a>
                                </li>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </ul>
                <a class="more" href="solr/novelList?queryString=武侠修真 ">MORE</a>
            </div>
        </div>
    </div>
    <div class="center_content">
        <h3 class="novel_type" >作品分类</h3>
        <ul data-mcs-theme="light-3">
            <c:forEach items="${categoryCount}" var="categorycount">
                <li class="ws_li">
                    <a href="#">
                        <c:set
                            var="picName" scope="request"  value="${categorycount.novelCategory}.jpg"/>
                        <a href="#"><img alt="" src="../img/novel-img/${picName}"/>
                                ${categorycount.novelCategory} ${categorycount.docCount}</a>
                            <%--<a href="#" style="white-space:pre-line;">总裁离魂小记</a>--%>
                    </a>
                </li>
            </c:forEach>

        </ul>
    </div>
    <div class="center_content">
        <h3 class="novel_type" >本周排行榜</h3>
        <ul data-mcs-theme="light-3">
            <c:forEach items="${weekBest}" var="weekbest">
                <li class="ws_li">
                    <a href="#">
                        <a href="#">
                            <img alt="" src="${weekbest.picUrl}"/>${weekbest.name}  ${weekbest.weekclick}</a>
                    </a>
                </li>
            </c:forEach>

        </ul>
    </div>
    <div class="center_content">
        <h3 class="novel_type" >本月排行榜</h3>
        <ul data-mcs-theme="light-3">
            <c:forEach items="${monthBest}" var="monthbest">
                <li class="ws_li">
                    <a href="#">
                        <a href="#">
                            <img alt="" src="${monthbest.picUrl}"/>${monthbest.name}  ${monthbest.monthclick}</a>
                    </a>
                </li>
            </c:forEach>

        </ul>
    </div>

    <%--连载榜--%>
    <div class="center_content">
        <h3 class="novel_type" >连载榜</h3>
        <ul data-mcs-theme="light-3">
            <c:forEach items="${serialBest}" var="serial">
                <li class="ws_li">
                    <a href="#">
                        <a href="#"><img alt="" src="${serial.picUrl}"/>${serial.name}  ${serial.countreCommend}</a>
                            <%--<a href="#" style="white-space:pre-line;">总裁离魂小记</a>--%>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <%--完结榜--%>
    <div class="center_content">
        <h3 class="novel_type" >完结榜</h3>
        <ul data-mcs-theme="light-3">
            <c:forEach items="${endBest}" var="end">
                <li class="ws_li">
                    <a href="#">
                        <a href="#"><img alt="" src="${end.picUrl}"/>${end.name}  ${end.countreCommend}</a>
                            <%--<a href="#" style="white-space:pre-line;">总裁离魂小记</a>--%>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <%--作家榜--%>
    <div class="center_content">
        <h3 class="novel_type" >大神俱乐部</h3>
        <ul data-mcs-theme="light-3">
            <c:forEach items="${topAuthor}" var="topAuthor">
                <li class="ws_li">
                    <a href="#">
                        <a href=""><img alt="" src="../img/nopic.jpg"/>${topAuthor.author}  ${topAuthor.clickCount}</a>
                            <%--<a href="#" style="white-space:pre-line;">总裁离魂小记</a>--%>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="button_content row">
        <div class="b_new_book col-sm-3">
            <div class="b_n_box ">
                <h3 class="novel_type ">最新小说</h3>
                <ul class="ul_list">
                    <li class="card-item">
                        <span class="digital_rank ">1</span>
                        <a href="# " class="novel_name ">总裁离魂小记</a>
                        <a href="# " class="author_name ">木三观</a>
                    </li>
                    <li class="card-item">
                        <span class="digital_rank ">2</span>
                        <a href="# " class="novel_name ">纸飞机</a>
                        <a href="# " class="author_name ">潭石</a>
                    </li>
                    <li class="card-item">
                        <span class="digital_rank ">3</span>
                        <a href="# " class="novel_name ">归路</a>
                        <a href="# " class="author_name ">白芥子</a>
                    </li>
                    <li class="card-item">
                        <span class="digital_rank ">4</span>
                        <a href="# " class="novel_name ">我成了我哥的地宝</a>
                        <a href="# " class="author_name ">阿阮有酒</a>
                    </li>
                    <li class="card-item">
                        <span class="digital_rank ">5</span>
                        <a href="# " class="novel_name ">滚蛋吧，Alpha！</a>
                        <a href="# " class="author_name ">冉尔</a>
                    </li>
                    <li class="card-item">
                        <span class="digital_rank ">6</span>
                        <a href="# " class="novel_name ">【ABO】投桃</a>
                        <a href="# " class="author_name ">风露沁酒</a>
                    </li>
                    <li class="card-item">
                        <span class="digital_rank ">7</span>
                        <a href="# " class="novel_name ">碰瓷界翘楚</a>
                        <a href="# " class="author_name ">陈隐</a>
                    </li>
                    <li class="card-item">
                        <span class="digital_rank ">8</span>
                        <a href="# " class="novel_name ">红五届</a>
                        <a href="# " class="author_name ">舍木氓生</a>
                    </li>
                    <li class="card-item">
                        <span class="digital_rank ">9</span>
                        <a href="# " class="novel_name">遵命</a>
                        <a href="# " class="author_name">麟潜</a>
                    </li>
                    <li class="card-item">
                        <span class="digital_rank">10</span>
                        <a href="# " class="novel_name">南风吹又生</a>
                        <a href="# " class="author_name">箫云封</a>
                    </li>
                </ul>
                <a class="more" href="# ">MORE</a>
            </div>
        </div>
        <div class="b_re_left col-sm-3">
            <div class="bre_l_box ">
                <h3 class="novel_type ">网游竞技</h3>
                <ul class="ul_list n_box">
                    <%
                        i = 0;
                    %>
                    <c:forEach items="${allNovel}" var="novel" varStatus="status">
                        <c:if test="<%=i<10%>">
                            <c:if test="${novel.category eq '网游竞技'}">
                                <%
                                    i = i + 1;
                                %>
                                <li class="card-item">
                                    <span class="digital_rank "><%=i%></span>
                                    <a href="openCatalog?nid=${novel.id}" class="novel_name ">${novel.name}</a>
                                    <a href="solr/openAuthor?nauthor=${novel.author}" class="author_name" target="iframe_a">${novel.author}</a>
                                </li>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </ul>
                <a class="more" href="solr/novelList?queryString=网游竞技">MORE</a>
            </div>
        </div>
        <div class="b_re_center col-sm-3">
            <div class="bre_c_box ">
                <h3 class="novel_type ">武侠仙侠</h3>
                <ul class="ul_list n_box">
                    <%
                        i = 0;
                    %>
                    <c:forEach items="${allNovel}" var="novel" varStatus="status">
                        <c:if test="<%=i<10%>">
                            <c:if test="${novel.category eq '武侠仙侠'}">
                                <%
                                    i = i + 1;
                                %>
                                <li class="card-item">
                                    <span class="digital_rank "><%=i%></span>
                                    <a href="openCatalog?nid=${novel.id}" class="novel_name ">${novel.name}</a>
                                    <a href="solr/openAuthor?nauthor=${novel.author}" class="author_name" target="iframe_a">${novel.author}</a>
                                </li>
                            </c:if>
                    </c:if>
                    </c:forEach>
                </ul>
                <a class="more" href="solr/novelList?queryString=武侠仙侠">MORE</a>
            </div>
        </div>
        <div class="b_re_right col-sm-3">
            <div class="bre_r_box ">
                <h3 class="novel_type  ">历史军事</h3>
                <ul class="ul_list n_box">
                    <%
                        i = 0;
                    %>
                    <c:forEach items="${allNovel}" var="novel" varStatus="status">
                        <c:if test="<%=i<10%>">
                            <c:if test="${novel.category eq '历史军事'}">
                                <%
                                    i = i + 1;
                                %>
                                <li class="card-item">
                                    <span class="digital_rank "><%=i%></span>
                                    <a href="openCatalog?nid=${novel.id}" class="novel_name ">${novel.name}</a>
                                    <a href="solr/openAuthor?nauthor=${novel.author}" class="author_name" target="iframe_a">${novel.author}</a>
                                </li>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </ul>
                <a class="more" href="solr/novelList?queryString=历史军事">MORE</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
