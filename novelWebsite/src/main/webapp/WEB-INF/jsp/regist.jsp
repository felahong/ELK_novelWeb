<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="UTF-8">
		<title>brother注册</title>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/regist.css" />
		<link rel="stylesheet" type="text/css" href="css/footer.css" />

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
					注册
				</span>
			</div>
			<div class="regist-container">
				<form action="userRegister.action" method="post" onsubmit="return regist()">
					<div class="center_div">
						<div class="form-group">
							<input type="text" name="username" placeholder="昵称（例：椰子芒果）" class="username" />
						</div>
						<div class="form-group">
							<input type="password" name="upassword" placeholder="密码（6-16个字符组成，区分大小写)" class="upassword" />
						</div>
						<div class="form-group">
							<input type="text" name="umail" placeholder="请输入邮箱号" class="umail" />
						</div>
						<div class="descript">
							<label>
							<input type="checkbox" value="1" id = "agree" />
							我已同意
							<a target = "_blank" href="#">《brother用户使用协议》</a>
							</label>
						</div>
						<input type="submit" name="submit" value="注册" class="go" />
						<div class="direct_login">
							<a href="login.html">已有账号，直接登录</a>
						</div>
					</div>
				</form>
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
	</body>

</html>