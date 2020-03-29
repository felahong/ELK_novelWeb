<%--
  Created by IntelliJ IDEA.
  User: 22969
  Date: 2019/1/2
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>更新章节</title>
    <script src="js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="Bootstrap/css/bootstrap.css">
    <script src="Bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/person-admin.css" />

    <style>
        .c_right {
            width: 100%;
            border: 1px solid silver;
        }
        .c_right .title {
            height: 30px;
            width: 100%;
            display:block;
        }
        .c_right .title p{
            color: #CD5C5C;
            margin-right: 16px;
            font-size: 20px;
            padding-left: 10px;
        }
        .c_right .novel-info {
            width: 100%;
            height: 590px;
            display: block;
            padding: 10px;
        }
        .c_right .novel-info .form-item {
            width: 80%;
            height: 60px;
            margin-bottom: 10px;
        }
        .c_right .novel-info .form-item:last-child {
            width: 80%;
            height: 200px;
            margin-bottom: 10px;
        }

        .c_right .novel-info .form-item .item-name {
            width: 200px;
            height: 30px;
        }

        .c_right .novel-info .form-item .item-cont {
            width: 400px;
            height: 200px;
        }

        .c_right .novel-info .c-button {
            margin-top: 180px;
            width: 100%;
            height: 68px;
        }
        .c-button .s_button{
            background: #CD5C5C;
            border: #CD5C5C solid 1px;
            color: white;
            height: 30px;
            width: 68px;
            margin-left:180px;
        }
    </style>
    <script>
        $(function () {
            //0.监听内容的实时输入
            $("body").delegate(".item-cont","propertychange input", function () {
                //判断是否输入了内容
                if($(this).val().length > 0){
                    //让按钮可用
                    $(".s_button").prop("disabled", false);
                }else{
                    //让按钮不可用
                    $(".s_button").prop("disabled", true);
                }
            });
        });
    </script>
</head>
<body>
    <div class="c_right">
        <div class="title"><p>作品信息</p></div>
        <div class="novel-info">
            <form action="submitChapter" method="post" class="form-horizontal" enctype="multipart/form-data">
                <input type="hidden" name="nid" value="${nid}">
                <input type="hidden" name="lastchapter" value="${lastchapter}">
                <div class="form-item form-group">
                    <label class=" col-sm-3 control-label">章节名称</label>
                    <div class="col-sm-8">
                        <input type="text" placeholder="第一章" class="item-name form-control"  id="chaptername" name="chaptername"/>
                    </div>
                </div>
                <div class="form-item form-group">
                    <label class=" col-sm-3 control-label">章节内容</label>
                    <textarea autocomplete="off" spellcheck="false" placeholder="请输入内容，100-13000字" id="content" class="item-cont form-control" name="content"/></textarea>
                </div>
                <div class="c-button">
                    <input type="button" value="提交" class="s_button" onclick="insertChapter('${nid}')"/>
                </div>
            </form>
        </div>
    </div>
    <script src="layer/layer.js"></script>

    <script type="text/javascript">
    function insertChapter(nid){

        var chaptername = $("#chaptername").val();
        var content = $("#content").val();
        $.ajax({
            type:"POST",
            url:"submitChapter",
            data:{"nid":nid,"chaptername":chaptername,"content":content},
            async:true,
            success:function(lastchapter) {
                $("#chaptername").val("");
                $("#content").val("");
                if(confirm('插入成功，是否查看该小说')){
                    //跳转界面
                    var form = document.createElement('form');
                    form.action = "content?nid=" + nid+"&chapterid="+lastchapter;
                    form.target = '_blank';
                    form.method = 'POST';
                    document.body.appendChild(form);
                    form.submit();
                }
            },
            error:function(){
                layer.alert("失败");
            }
        });
    }
</script>
</body>
</html>
