<%--
  Created by IntelliJ IDEA.
  User: 22969
  Date: 2019/1/2
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8"%>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>个人中心</title>
    <script src="js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="Bootstrap/css/bootstrap.css">
    <script src="Bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/person-admin.css" />
</head>
<body>
<%@ include file="head2.jsp"%>
<div id="center">
    <div class="c_left">
        <ul class="nav nav-pills nav-stacked">
            <li>
                <a href="personMain" target="iframe_b"><span class="glyphicon glyphicon-user" style="font-size: 15px;"></span>&nbsp;&nbsp;个人中心</a>
            </li>
            <li>
                <a href="#"><span class="glyphicon glyphicon-book" style="font-size: 15px;"></span>&nbsp;&nbsp;最近阅读</a>
            </li>
            <li>
                <a href="#"><span class="glyphicon glyphicon-tasks" style="font-size: 15px;"></span>&nbsp;&nbsp;账户管理</a>
            </li>
            <li>
                <a href="#"><span class="glyphicon glyphicon-send" style="font-size: 15px;"></span>&nbsp;&nbsp;我的订阅</a>
            </li>
            <li>
                <a href="#"><span class="glyphicon glyphicon-tree-deciduous" style="font-size: 15px;"></span>&nbsp;&nbsp;我的书架</a>
            </li>
            <li>
                <a href="javascript:;" id="author-index"><span class="glyphicon glyphicon-leafglyphicon glyphicon-user" style="font-size: 15px;"></span>&nbsp;&nbsp;作者中心</a>
            </li>
        </ul>
    </div>

    <iframe src="personMain" name="iframe_b" width="78%" onload="setIframeHeight(this)" frameborder="0" scrolling="no"></iframe>

</div>
<input type="hidden" value="${user.uid}" id="uid">
<script src="layer/layer.js"></script>
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

    $(function(){
        $("#author-index").click(function () {
            var uid = $("#uid").val();
            $.ajax({
                type:"POST",
                url:"judgeAuthor",
                data:{"uid":uid},
                async:true,
                success:function(data) {
                    console.log(data);
                    if(data == "isAuthor"){
                        var form = document.createElement('form');
                        form.action = "authorIndex";
                        form.target = '_blank';
                        form.method = 'POST';
                        document.body.appendChild(form);
                        form.submit();
                    }else if(data == "notAuthor"){

                        if(confirm('亲还不是作者，是否激活作者身份？')){
                            //跳转界面
                            var form = document.createElement('form');
                            form.action = "activeAuthor";
                            form.target = '_blank';
                            form.method = 'POST';
                            document.body.appendChild(form);
                            form.submit();
                        }
                    }else if(data == "noUser"){
                        layer.alert("亲还没有登录哦！！！");
                    }
                },
                error:function(){
                    layer.alert("失败");
                }
            });
        })
    })
</script>
</body>
</html>
