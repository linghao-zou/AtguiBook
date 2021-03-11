<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>尚硅谷会员注册页面</title>
<!--		通过base标签永远固定 运行的相对路径-->

		<base href="http://localhost:8080/Book/">
		<link type="text/css" rel="stylesheet" href="static/css/style.css" >
		<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
		<script type="text/javascript">
			$(function () {

				$("#code_img").click(function (){
					//在实践中相应的function函数中有this对象 这个this
					//是当前正在响应事件的dom对象
					this.src="Kaptcha.jpg?d="+new Date();
				});

				$("#username").blur(function (){
					var username=this.value;
					$.getJSON("http://localhost:8080/Book/userServlet","action=ajaxExistsUsername&username="+username,function (data) {
						if(data.existsUsername){
							$("span.errorMsg").text("用户名已存在!");
						}else{
							$("span.errorMsg").text("用户名可用!")
						}
					})
				});

				$("#sub_btn").click(function () {
					//1、获取用户名输入框里面的内容
					var usernameText=$("#username").val();
					//2、创建正则表达式对象
					var usernamePatt=/^\w{5,12}$/;
					//3、通过正则表达式对象的test方法
					if (!usernamePatt.test(usernameText)){
						//4、提示用户结果
						$("span.errorMsg").text("用户不合法");
						return false;
					}
					var passwordText=$("#password").val();

					var passwordPatt=/^\w{5,12}$/;
					if (!passwordPatt.test(passwordText)){
						$("span.errorMsg").text("用户名密码不合法");
						return  false;
					}

					//1、获取确认密码的内容 和密码相比较
					var repwdText=$("#repwd").val()
					if (repwdText!=passwordText){
						$("span.errorMsg").text("确认密码和密码不一致");
						return  false;
					}

					//获取邮箱里的内容
					var email=$("#email").val()
					//创建正则表达式对象
					var emailPatt=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
					if (!emailPatt.test(email)){
						$("span.errorMsg").text("邮箱信息不合法");
						return false;
					}
					//使用test方法验证是否合法
					var codeText = $("#code").val();
					var codeText = $.trim(codeText);
					if (codeText==null||codeText==""){
						$("span.errorMsg").text("验证码不能为空！")
						return false;
					}
					//提示用户

					$("span.errorMsg").text("");

				});



			});

		</script>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	</head>
	<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
								${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="POST" >
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   value="${requestScope.username}"
										   autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   value="${requestScope.email}"
										   autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 80px;"  name="code" id="code"/>
									<img id="code_img" alt="" src="Kaptcha.jpg" style="width:110px;height: 30px;  float: right; margin-right: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		<%@ include file="/pages/common/foot.jsp"%>

	</body>
</html>