//加载事件绑定
$(function(){
	var code='';
	//绑定获取邮箱验证码按钮事件
	$("#verifyBtn").click(function(){
		//判断邮件地址不为空
		let email=$("#email").val();
		//不为空
		if(email){
			//远程请求路径：http://localhost:7070/video/
			let url=basePath+"email/code";
			let data={
				email: $("#email").val()
			}
			//点击触发执行
			$.post(url,data,function(res){
				console.log(res);
				//后端返回正确
				if(res.errorCode==0){
					//后端返回数据
					code = res.data;
				}
				
			});
		}else{
			alert("邮件地址不可以为空");
		}
	});
	
	//注册按钮
	$("#registerBtn").click(function(){
		//1、验证email不为空
		//2、验证码不为空
		//3、验证码是否正确
		 if(code!= $("#verifyCode").val()){
			 // return false;
		 }
		//4、验证确认密码与密码相同
		let pass=$("#pass").val(); //密码
		let verifyPass=$("#verifyPass").val(); //确认密码
		if(pass==verifyPass){
			let url=basePath+"register";
			let data={
				email: $("#email").val(),
				verifyCode:  $("#verifyCode").val(),
				pass: $("#pass").val(),
				username:  $("#email").val()
			}
			//ajax请求
			$.post(url,data,function(result){
				console.log(result.data);
				//后端返回成功
				if(result.errorCode==0){
					let user=result.data;
					
					location.href="login.html"
				}
			})
		}
	});
	
});