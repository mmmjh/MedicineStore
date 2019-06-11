<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>注册界面</title>
<link href="./Wopop_files/style_log.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="./Wopop_files/style.css" />
<link rel="stylesheet" type="text/css" href="./Wopop_files/userpanel.css" />
<link rel="stylesheet" type="text/css" href="./Wopop_files/jquery.ui.all.css" />
<script>
function toregister()
{
	window.location.href="regist.jsp";
}
function validate() {
	var inputCode = document.getElementById("password").value;//获取输入框内验证码并转化为大写  
	var inputCodep = document.getElementById("password1").value;//获取输入框内验证码并转化为大写  
	var inputCode1 = document.getElementById("studentname").value;//获取输入框内验证码并转化为大写
	var inputCode2 = document.getElementById("yanzhengma").value;
	if(inputCode1=="") { //若输入的验证码长度为0 
	 alert("请输入用户名！"); //则弹出请输入验证码 
	 document.getElementById("password").value="";
	 return 0;
	} 
	else if(inputCode=="") { //若输入的验证码长度为0 
		 alert("请输入密码！"); //则弹出请输入验证码 		
		 document.getElementById("password").value="";
		 return 0;
		} 
	else if(inputCode!=inputCodep){
			alert("两次密码不一致");
			 return 0;
		}
	else if(inputCode2==""){
		 alert("请输入验证码！");
		 return 0;
	}
	else return 1;
}
function test() {
    top.Dialog.alert("如果丢失密码，请与0311-85813216电话联系！");
}
</script>
 
</head>
<body class="login" >
<div class="login_m" style="height:1500px">
<div class="login_logo" style="width:1000px;margin-left:-300px;margin-bottom:60px"><h1 >药&nbsp;&nbsp;店&nbsp;&nbsp;管&nbsp;&nbsp;理&nbsp;&nbsp;系&nbsp;&nbsp;统</h1></div>
<div class="login_boder"  style="height:1000px">
<form action="reg.jsp" onsubmit="return validate()==1" method="get"class="agile_form">
<div class="login_padding" id="login_model" style="height:1500px">
<%
	request.setCharacterEncoding("UTF-8");
	String error = (String)request.getAttribute("loginError");
	if(error == null)	error="";
%>
<input id="pd" type="hidden" value="<%=error %>" />
<h2>用户&nbsp;名</h2>
  <label>
       <input  type="text" name="username" id="username" class="txt_input" placeholder="请输入用户名" />
  </label>
  <h2>密&nbsp;&nbsp;&nbsp;码</h2>
  <label>
    <input type="password" name="password" id="password" class="txt_input"   placeholder="请输入密码" />
  </label>
  <h2>确&nbsp;&nbsp;&nbsp;认</h2>
  <label>
    <input type="password" name="password1" id="password1" class="txt_input"   placeholder="请确认密码" />
  </label>
  <img class="shuaxin" id="tu" src="yan/randCode.jsp" width="65" height="36" onclick="this.src='yan/randCode.jsp?s='+new Date().getTime();"  />
 	<h2 >验证&nbsp;码</h2>
  <label>
    <input type="text" name="yanzhengma" id="yanzhengma" class="txt_inputyanzheng"   placeholder="输入验证码" />
    </label>
	<p class="forgot"><a id="iforget"  href="login.jsp">返回登录</a></p>
	
  	<div class="rem_sub" style="width:300px">
    <label style="width:300px">
       <input type="submit" name="button" id="button" value="注册" 
       style="width:322px;border-radius:5px;height:35px;margin-top:20px;margin-left:30;background-color:#0080ff"  />
    </label>
  </div>
</div>
</form>
  </div>
</div>

<script type="text/javascript">
	window.onload=function(){
		var pd = document.getElementById("pd").value;
		if(pd == "") return;
		else alert(pd);
	}
</script>
</body>
</html>