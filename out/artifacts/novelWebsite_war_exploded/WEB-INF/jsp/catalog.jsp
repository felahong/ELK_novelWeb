<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="UTF-8">
		<title>目录</title>
        <script src="../js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" href="../Bootstrap/css/bootstrap.css">
		<script src="../Bootstrap/js/bootstrap.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/common.css" />
		<link rel="stylesheet" type="text/css" href="../css/catalog.css"/>
	</head>

	<body>
		<!--头部-->
		<%--<%@ include file="head.jsp"%>--%>

		<!--中部-->
		<div id="novel_center">
			<div class="cen_novel">
				<div class="c_topic_img">
					<img src="${novel.picUrl }" />
				</div>
				<div class="c_topic_intro">
					<div>
						<span class="info-name">${novel.name }</span>
					</div>
					<div class="info-tag">
						<span class="info-detail">作者:</span>
						<a href="solr/openAuthor?nauthor=${novel.author}">${novel.author }</a>&nbsp;&nbsp;
						<span class="info-detail">类别:</span>
                        <a href="solr/novelList?queryString=${novel.category}" >${novel.category}</a>
						<span class="info-detail">状态:</span>
						<a href="solr/novelList?queryString=${novel.status}">${novel.status}</a>
					</div>
					<div class="info-pop">
                        <span class="info-detail">点击:</span>
                        <label class="info-state">${novel.clickcount}</label>&nbsp;
                        <span class="info-detail">总推荐:</span>
                        <label class="info-state">${novel.countreCommend}</label>&nbsp;
                        <span class="info-detail">月推荐:</span>
                        <label class="info-state">${novel.monthreCommend}</label>&nbsp;
                        <span class="info-detail">周推荐:</span>
                        <label class="info-state">${novel.weekreCommend}</label>&nbsp;
                        <span class="info-detail">收藏:</span>
                        <label class="info-state">${novel.collect}</label>
                        <span class="info-detail">字数:</span>
                        <label class="info-state">${novel.count}</label>
					</div>
					<div class="info-click">
						<button type="button" class="info-read" onclick="readContent('${novel.id}')">
							<span>点击阅读</span>
						</button>
						<div class="btn-group">
                            <a href="updateCollect?nid=${novel.id}&type=collect">收藏</a>
                            <a href="updateCollect?nid=${novel.id}&type=dashang">打赏</a>
                            <%--<c:if test="${(param.info_msg != '') && !(empty param.info_msg) }">--%>
                                <%--<script> alert("${param.info_msg}");</script>--%>
                            <%--</c:if>--%>
                            <c:if test="${(info_msg != '') && !(empty info_msg) }">
                                <%--<script> alert("正在努力开发中...");</script>--%>
                                <script> alert("${info_msg}");</script>
                            </c:if>
                            <a href="updateCollect?nid=${novel.id}&type=recommend">推荐</a>
                            <a href="#">分享</a>
						</div>
					</div>
				</div>
			</div>
			<div class="cen_simple">
				<p class = "cen_s_title">文章简介</p>
				<input type="hidden" value="${novel.novelInfo }" id="intro">
				<span class = "cen_s"></span>
			</div>
			<div class="cen_catalog">
				<p class = "cen_s_title">目录</p>
				<ul class = "cata_list">
					<c:forEach items="${chapterList }" var="chapter" varStatus="status">
						<%--&name=${novel.name}&chapterid=${chapter.chapterName }&author=${novel.author}--%>
						<%--<li><a href="content?nid=${chapter.novelId }"--%>
                        <li><a href="content?nid=${novel.id}&name=${novel.name}&chapter=${chapter.chapterName}&author=${novel.author}"
                               method="post" target="_blank"><span>${chapter.chapterName }</span></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<script src="layer/layer.js"></script>
		<script type="text/javascript">
            function readContent(nid) {
                $.ajax({
                    type:"POST",
                    url:"judgeCatalog",
                    data:{"nid":nid,"chapterid":1},
                    async:true,
                    success:function(data) {
                        console.log(data);
                        if(data == "noBigger"){
                            layer.alert("作者还没有开始动笔呢");
                        }else if(data =="turn"){
                            var form = document.createElement('form');
                            form.action = "content?nid=" + nid+"&chapterid=1";
                            form.target = '_blank';
                            form.method = 'POST';
                            document.body.appendChild(form);
                            form.submit();
                        }
                    },
				})
            }

            $(function(){
				var intro = $("#intro").val();
                var array = intro.split("\n");
                $.each(array, function (index, value) {
                    $(".cen_s").append("<p>" + value.replace(/(^\s*)/g," ") + "</p><br>");
                });
			})
		</script>
	</body>

</html>