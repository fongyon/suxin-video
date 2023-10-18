var basePath="http://localhost:7070/video/"
$(function(){
	//从sessionStorage中取得token
	let token=sessionStorage.getItem("access_token");
	if(token){
		$.ajaxSetup({
			headers:{
				'Authentication':token  //通过header发送口令牌到后端服务
			}
		});
	}
});