<%--
  Created by IntelliJ IDEA.
  User: 22969
  Date: 2019/1/2
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8"%>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>新建小说</title>
    <script src="js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="Bootstrap/css/bootstrap.css">
    <script src="Bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/person-admin.css" />
    <style type="text/css">
        .c_right {
            width: 100%;
            border: 1px solid silver;
        }

        .c_right .c_info {
            width: 50%;
            height: 450px;
            display: inline-block;
        }
        .c_right .c_info .title {
            height: 30px;
            width: 100%;
            display:block;
        }
        .c_right .c_info .title p{
            color: #CD5C5C;
            margin-right: 16px;
            font-size: 20px;
            padding-left: 10px;
        }
        .c_right .c_info .novel-info {
            width: 100%;
            height: 590px;
            display: block;
            padding: 10px;
        }
        .c_right .c_info .novel-info .form-item {
            width: 100%;
            height: 60px;
        }
        .c_right .c_info .novel-info .item-input {
            width: 160px;
            height: 30px;
            border: solid silver 1px;
            border-radius: 2px;
        }
        .c_right .c_info .novel-info .state_select {
            height: 30px;
            border: solid silver 1px;
            border-radius: 2px;
        }
        .c_right .c_info .novel-info .item-type {
            width: 160px;
            height: 30px;
            border: solid silver 1px;
            border-radius: 2px;
        }
        .c_right .c_info .novel-info .item-info {
            width: 200px;
            height: 60px;
            border: solid silver 1px;
            border-radius: 2px;
        }
        .c_right .c_info .novel-info .item-label {
            font-size: 16px;
            margin-right: 10px;
        }
        .c_right .c_info .novel-info .form-item {
            width: 100%;
            height: 60px;
        }
        .c_right .right_c {
            width: 48%;
            margin-top: 40px;
            height: 410px;
            float: right;
        }
        .c_right .c-button {
            width: 100%;
            height: 68px;
        }
        .c_right .c-button input {
            background: #CD5C5C;
            border: #CD5C5C solid 1px;
            color: white;
            height: 30px;
            width: 68px;
            margin:0 auto;
        }
    </style>

</head>
<body>
<div id="center">
    <div class="c_right">
        <form id= "form" action="submitNovel" method="post" class="form-horizontal">
            <input type="hidden" id= "uid" value="${user.uid}">
            <div class="c_info">
                <div class="title"><p>作品信息</p></div>
                <div class="novel-info">
                    <div class="form-item form-group">
                        <label class="col-sm-3 control-label">作品名称</label>
                        <div class="col-sm-8">
                            <input type="text" placeholder="请输入作品名称" class="form-control" name="name" />
                        </div>
                    </div>
                    <div class="form-item form-group">
                        <label class="col-sm-3 control-label">作品进程</label>
                        <div class="col-sm-8">
                            <select class="form-control" name="status">&nbsp;&nbsp;
                                <option value="完结">完结</option>
                                <option value="连载中">连载中</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-item form-group">
                        <label class="col-sm-3 control-label">作品分类</label>
                        <div class="col-sm-8">
                            <select class="form-control" name="category">&nbsp;&nbsp;
                                <option value="都市言情">都市言情</option>
                                <option value="武侠修真">武侠修真</option>
                                <option value="科幻灵异">科幻灵异</option>
                                <option value="武侠仙侠">武侠仙侠</option>
                                <option value="历史军事">历史军事</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-item form-group">
                        <label class="col-sm-3 control-label">作品标签</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="detail" />
                        </div>
                    </div>
                    <div class="form-item form-group">
                        <label class="col-sm-3 control-label">作品封面</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="picUrl" />
                        </div>
                    </div>
                    <div class="form-item form-group">
                        <label class="col-sm-3 control-label">作者名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="author" />
                        </div>
                    </div>
                    <div class="form-item form-group">
                        <label class="col-sm-3 control-label">作品介绍</label>
                        <div class="col-sm-8">
                            <textarea autocomplete="off" spellcheck="false" placeholder="20-500字" class="form-control" name="novelInfo" /></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <%--<div class="right_c">--%>
            <%--<div class = "form-group" style="border: 1px dashed silver; width: 240px; height: 240px;">--%>
            <%--上传封面--%>
            <%--<div style="width: 100px; height: 150px; border: 1px solid silver; padding: 5px;">--%>
            <%--<img id="xmTanImg" style="width: 90px; height: 140px;"/>--%>
            <%--</div>--%>

            <%--<div id="xmTanDiv"></div>--%>
            <%--<p>--%>
            <%--<input type="file" id="xdaTanFileImg" onchange="xmTanUploadImg(this)" name="pictureFile"/>--%>
            <%--</p>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="form-group c-button">
                <input type="button" class="form-control" value="提交" onclick="createNovel()" />
                <%--<input type="submit" class="form-control" value="提交" onclick="createNovel()"/>--%>
            </div>
        </form>
    </div>
</div>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="layer/layer.js"></script>
<script type="text/javascript">
    //判断浏览器是否支持FileReader接口
    if(typeof FileReader == 'undefined') {
        document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
        //使选择控件不可操作
        document.getElementById("xdaTanFileImg").setAttribute("disabled", "disabled");
    }

    //选择图片，马上预览
    function xmTanUploadImg(obj) {
        var file = obj.files[0];

        console.log(obj);
        console.log(file);
        console.log("file.size = " + file.size); //file.size 单位为byte

        var reader = new FileReader();

        reader.onload = function(e) {
            console.log("成功读取....");

            var img = document.getElementById("xmTanImg");
            img.src = e.target.result;
            //或者 img.src = this.result;  //e.target == this
        }

        reader.readAsDataURL(file)
    }

    function createNovel() {
        $.ajax({
            type:"POST",
            url:"submitNovel",
            data:new FormData($("#form")[0]),
            async:false,
            cache: false,
            contentType: false,
            processData: false,
            success:function() {
//                layer.alert("提交成功");
                window.location.replace("novelManage");
            },
            error:function(){
                layer.alert("失败");
            }
        });
    }

</script>
</body>
</html>
