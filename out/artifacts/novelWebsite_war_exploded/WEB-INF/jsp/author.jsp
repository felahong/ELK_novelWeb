<%--
  Created by IntelliJ IDEA.
  User: 22969
  Date: 2019/1/5
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/header.css">
    <style>
        #center {
            width: 80%;
            margin: 0 auto;
        }
        #center #author_intro{
            width: 100%;
            height: 150px;
            background: #f5f5f5;
            margin-top: 10px;
            border-radius: 2%;
            padding-left: 50px;
            padding-top: 30px;
        }
        #author_intro .author_img{
            width: 100px;
            height: 100px;
            border-radius: 50%;
            border: 0.05rem solid #eee;
            padding: 5px;
            float: left;
            background: yellow;
        }
        .author_img img{
            width: 100%;
            height: 100%;
            border-radius: 50%;
        }
        #author_intro .author_main{
            position: absolute;
            width: 600px;
            height: 100px;
            float: left;
            margin-left: 120px;
        }
        .author_main .info-name{
            font-size: 16px;
            font-weight: bold;
            height: 40px;
            line-height: 40px;
        }
        .author_main .author_statis{
            height: 30px;
            line-height: 30px;
        }
        .author_statis span{
            font-size: 14px;
            display: inline-block;
            margin-right: 15px;
        }
        .author_main .info-desc{
            padding: 5px 0px;
            line-height: 20px;
            font-size: 14px;

        }
        #author_intro .btn{
            width: 200px;
            height: 100px;
            float: right;
            padding-top: 40px;
        }
        #center #new_update{
            width: 100%;
            height: 200px;
            margin-top: 5px;
        }
        .n_title{
            padding: 16px 8px 0px 0px;
            margin-bottom: 16px;
            height: 40px;
            width: 80px;
            font-size: 17px;
            line-height: 20px;
            border-bottom: palevioletred 3px solid;
        }
        h3{
            display: block;
            font-size: 1.17em;
            font-weight: bold;
        }
        .novel-list{
            width: 80%;
            height: 130px;
            margin-bottom: 20px;
        }
        .novel-list-item{
            width: 100%;
            height: 130px;
            float: left;
            padding-left: 5px;
            border-radius: 5%;
        }
        .novel-list-item .item-img{
            width: 10%;
            height: 130px;
            float: left;
            margin-right: 20px;
        }
        .item-img img{
            width: 100%;
            height: 130px;
            border-radius: 3%;
        }
        .novel-list-item .box1{
            width:60%;
        }
        .box1 .item-info{
            margin-bottom: 6px;
            box-sizing: border-box;
            -webkit-tap-highlight-color: transparent;
            display: block;
            color: #495060;
        }
        .box1 .item-info .item-name{
            float: left;
            height: 24px;
            line-height: 24px;
            font-size: 16px;
            font-weight: bold;
            color: #333;
            margin-right: 25px;
        }
        .box1 .item-info span{
            color: #999;
            line-height: 24px;
        }
        span{
            font-size: 14px;
        }
        .item-publish-time{
            float: right;
            color: #333;
            line-height: 24px;
        }

        .item-statis{
            margin-bottom: 6px;
            font-size: 14px;
        }

        .item-statis a{
            display: inline-block;
            height: 24px;
            line-height: 24px;
            font-size: 12px;
            margin-right: 15px;
            color: #999;
            font-size: 14px;
        }

        .item-desc{
            margin-bottom: 6px;
            padding-bottom: 6px;
            height: 30px;
            line-height: 24px;
            border-bottom: 0.05rem solid #eee;
            color: #666;
            overflow: hidden;
        }
        .novel-list-item .box2{
            width:600px;
        }
        .item-update{
            float: left;
        }
        .item-update-name{
            margin-right: 30px;
        }

        .item-update-name a{
            float: left;
            height: 24px;
            font-size: 14px;
            color: #999;
            line-height: 24px;
        }

        .item-update-time{
            height: 24px;
            line-height: 24px;
            font-size: 14px;
            color: #999;
        }

        #center #all_novel{
            width: 100%;
            margin-top: 20px;
            border: 1px solid #CCCCCC;
        }

    </style>
    <script src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".novel-list").hover(function(){
                $(this).css("background","#CCCCCC");
            },function (){
                $(this).css("background","none");
            });
        })

    </script>
</head>
<body>

<div id="center">
    <div id="author_intro">
        <div class="author_img">
            <img src="img/author.png" />
        </div>
        <div class="author_main">
            <h3 class="info-name">${nauthor}</h3>
            <div class="author_statis">
                <span>粉丝：1024500</span>
                <span>粉丝数：10</span>
                <span>专栏字数：234.2 W</span>
            </div>
            <div class="info-desc">
                2019年也要超幸运哦。
            </div>
        </div>
        <div class="btn">
            <button type="button" class="author_btn">
                <span>关注TA</span>
            </button>
        </div>
    </div>
    <%--<c:if test="newNovel ne null">--%>
    <div id="new_update">
        <h3 class="n_title">
            <p>最近更新</p>
        </h3>
        <div class="novel-list">
            <div class="novel-list-item">
                <div class="item-img">
                    <img src="/brotherNovel/pic/${newNovel.nlogo}" alt="">
                </div>
                <div class="box1">
                    <div class="item-info">
                        <a href="" class="item-name">
                            ${newNovel.nname}
                        </a>
                        <c:if test="${newNovel.nlabel} eq null">
                            <span>无标签</span>
                        </c:if>
                        <c:if test="${newNovel.nlabel} ne null">
                            <span>${newNovel.nlabel}</span>
                        </c:if>
                        <em>|</em>
                        <c:if test="${newNovel.nstatic} eq 0">
                            <span>连载</span>
                        </c:if>
                        <c:if test="${newNovel.nlabel} eq 1">
                            <span>完结</span>
                            </c:if>
                        <em>|</em>
                        <span>20万字</span>
                        <span class="item-publish-time">发表时间：<fmt:formatDate value="${newNovel.ncreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                    </div>
                    <div class="item-statis">
                        <a href="">阅读：1234578</a>
                        <a href="">收藏：76543</a>
                    </div>
                    <div class="item-desc">
                        ${newNovel.nintro}
                    </div>
                </div>
                <div class="box2">
                    <span class="item-update">最近更新：</span>
                    <a href="" class="item-update-name">
                        ${newNovel.chaptername}
                    </a>
                    <span class="item-update-time"><fmt:formatDate value="${newNovel.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                </div>
            </div>

        </div>
    </div>
    <%--</c:if>--%>
    <%--<c:if test="searchNovel ne null">--%>
        <div id="all_novel">
        <h3 class="n_title">
            <p>全部作品</p>
        </h3>
        <ul>
            <c:forEach items="${searchNovel.novelList}" var = "novel" varStatus="status">
                <li>
                    <div class="novel-list">
                        <div class="novel-list-item">
                            <div class="item-img">
                                <img src="/brotherNovel/pic/${novel.nlogo}" alt="">
                            </div>
                            <div class="box1">
                                <div class="item-info">
                                    <a href="" class="item-name">
                                            ${novel.nname }
                                    </a>
                                    <span>${novel.nlabel }</span>
                                    <em>|</em>
                                    <span>
                                        <c:if test="${novel.nstatic eq 0}">
                                            连载
                                        </c:if>
                                        <c:if test="${novel.nstatic eq 1}">
                                            完结
                                        </c:if>
                                    </span>
                                    <em>|</em>
                                    <span>20万字</span>
                                    <span class="item-publish-time"><fmt:formatDate value="${novel.ncreatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                </div>
                                <div class="item-statis">
                                    <a href="">阅读：1234578</a>
                                    <a href="">收藏：76543</a>
                                </div>
                                <div class="item-desc">
                                    ${novel.nintro}
                                </div>
                            </div>
                            <div class="box2">
                                <span class="item-update">最近更新：</span>
                                <a href="" class="item-update-name">
                                    ${novel.chaptername}
                                </a>
                                <span class="item-update-time"><fmt:formatDate value="${novel.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            </div>
                        </div>
                    </div>
                </li>


            </c:forEach>
        </ul>
    </div>
    <%--</c:if>--%>
</div>
</body>
</html>