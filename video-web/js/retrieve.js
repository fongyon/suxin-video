$(function(){
	/**
	 * 验证码
	 */
	var verifyCode =new GVerify({
		id : "verify",
		type : "number"
	});
	//绑定事件
	$("#registerBtn").click(function(){
		let code=$("#verifyCode").val();
		let res= verifyCode.validate(code);//验证码验证
		if(res){
			return res;
		}else{
			return false;
		}
	});
	

	$("#loginBtn").click(function(){
		// 验证邮箱的地址与邮箱的验证码是否匹配
		let emailCode=$("#emailCode").val();//输入的邮箱验证码
		let hidCode=$("#hidCode").val();//发送邮箱验证码后返回值
		
		//前端验证
		// if(emailCode==hidCode){
		// 	//验证通过
		// 	location.href="reset.html";
		// }
		// 后端验证
		let url=basePath+"email/checkCode";
		let data={
			email:  $("#email").val(),
			emailCode: emailCode
		}
		$.post(url,data,function(result){
			if(result.errorCode==0){
				if(result.data=="true"){
					localStorage.setItem("email",$("#email").val());
					location.href="reset.html";
				}
			}
		});
	});
	
	//获取邮箱验证码
	$("#verifyBtn").click(function(){
		$("#registerBtn").click();
		//判断邮件地址不为空
		let code=$("#verifyCode").val();
		let res= verifyCode.validate(code);//验证码验证
		//验证码是否正确
		//不为空
		if(res && email){
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
					// code = res.data;
					$("#hidCode").val(res.data);
				}
				
			});
		}else{
			alert("邮件地址不可以为空或验证码不正确");
		}
	});
	
});