<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="UTF-8">
		<title>brother登录</title>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/login.css" />
		<link rel="stylesheet" type="text/css" href="css/footer.css"/>
	</head>

	<body>

		<!--头部-->
		<div id="login_header">
			<div class="h_top">
				<div class="h_list">
					<a href="#" title="首页">首页</a>
					<a href="#" title="小说专区">小说专区</a>
					<a href="#" title="视频专区">视频专区</a>
					<a href="#" title="同人专区">同人专区</a>
					<a href="#" title="周边区">周边区</a>
					<a href="#" title="讨论栏">讨论栏</a>
				</div>
				<div class="h_search">
					<form action="#">
						<input type="text" placeholder="输入你想要的信息" class="t_input">
						<input type="submit" value="" class="t_button">
					</form>
				</div>
			</div>
			<div class="h_img">
			</div>
		</div>
		<div id="center">
			<div class="title-line">
				<span class="tit" style="font-size: 38px;"> 
					登录
				</span>
			</div>
			<div class="login-box">
				<div class="login-left">
					<img src="img/hei1.jpg"/>
				</div>
				<div class="line"></div>
				<div class="login-right">
					<form action="userLogin" method="post" id="form">
					<ul>
						<li class="username">
							<input type="text" name="username" placeholder="你的用户" id="login-username" maxlength="50">
							<span class="status"></span>
						</li>

						<li class="password">
							<input type="password" name="upassword" placeholder="密码" id="login-passwd">
							<span class="status"></span>
						</li>

						<li class="check">
							<!--验证码-->
							<input type="text" placeholder="验证码" name="code">
							<%--<img src="CheckCodeServlet" onclick="change(this)">--%>
							<img id="codeImg" alt="验证码" src="code" onclick="change(this)"/>
						</li>

						<li class="remember">
							<label>
								<input type = "checkbox" name = "isRemember"/>
								记住我
								<span>不是自己电脑不要勾选此选项</span>
								<a href="#" class = "forget">忘记密码？</a>
								<a target="_blank" href="#" class = "not-checkout">无法验证</a>
							</label>
							<!--是否保存密码-->
						</li>
						<li class="btn-box">
							<input type="button" class="btn-login" value="登录">
							<a href="regist" class="btn-regist">注册</a>
						</li>
						<li class= "thrid-login">
							<a class="weibo-login">
								<img src="img/weibo.png" style="width: 23px; height:23px;"/>
								微博账号登录
							</a>
							<a href="turnQQ.html" class="qq-login">
								<img src="img/qq.png" style="width: 23px; height:23px;"/>
								QQ账号登录
							</a>
						</li>
					</ul>
					</form>
				</div>
			</div>
		</div>
		<div id="footer">
			<div class="end">
				<a href="#">关于我们</a>
				|
				<a href="#">联系方式</a>
				|
				<a href="#">联系客服</a>
				|
				<a href="#">关于投稿</a>
				|
				<a href="#">常见问题</a>
			</div>
			<p>Copyright @ 2020 felahong 版权所有</p>
		</div>
		<input id="activeName" type="hidden" value="${activeUser.username}">

		<script src="js/jquery-1.11.3.min.js"></script>
		<script src="layer/layer.js"></script>
		<script type="text/javascript">
            /*改变验证码*/
			function change(obj){
                obj.src="code?time="+new Date().getTime();
            }
            /*核对信息*/
            function checkData(){
                //1.获取标签
                var username = document.getElementById("username");
                var pwd1 = document.getElementById("pwd1");
                //2.判断输入的内容不能为空
                if(username.value == ""){
                    layer.alert('请输入用户名', {
                        icon: 2,
                        skin: 'layer-ext-moon'
                    });
                    return;
                }
                if(pwd1.value==""){
                    layer.alert('请输入密码', {
                        icon: 2,
                        skin: 'layer-ext-moon'
                    });
                    return;
                }
            }
            /*激活信息*/
            $(function() {
                /*激活邮箱*/
                var activeName = $("#activeName").val();
                console.log(activeName);
                if (!(activeName == null || activeName == "")) {
                    layer.msg('恭喜账号'+activeName+',您的账号激活成功');
                }
            }),
				/*判断登录*/
            $(function(){
           	 $(".btn-login").click(function () {

                    $.ajax({
                        type: "POST",
                        url: "userLogin",
                        data: new FormData($("#form")[0]),
                        async: false,
                        cache: false,
                        contentType: false,
                        processData: false,
                        success: function (data) {
                            if (data == "errCode") {
                                layer.alert('验证码错误')
                            } else if (data == "notActive") {
                                layer.alert('用户未激活')
							} else if (data == "errUser") {
                                layer.alert("用户名密码未匹配");
                            } else if (data == "defectInfo") {
                                layer.alert("请填完输入框的内容");
                            } else if (data == "succ") {
                                window.location.replace("index");
                            }
                        },
                        error: function () {
                            layer.alert("失败");
                        }
                    })
                })
			})


		</script>

</body>
</html>