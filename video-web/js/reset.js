$(function(){
	$("#registerBtn").click(function(){
		// alert('OK')
		//验证输入的密码一致
		let pass=$("#pass").val();
		let verifyPass=$("#verifyPass").val();
		//验证密码是否合法 6~18
		let regex=/^[a-zA-Z\d]{6,18}$/

		if(regex.test(pass)){ //验证密码合法性
			if(pass==verifyPass){
				//提交后台修改密码
				let url=basePath+"retrieve";
				let data={
					email: localStorage.getItem("email"),
					pass: pass
				}
				
				//找回密码按钮
					$.post(url,data,function(res){
						if(res.errorCode==0){
							// if(res.data){
								location.href="login.html";
							// }
						}
						// console.log(res.message);
					});
			}
		}
		
	});
})