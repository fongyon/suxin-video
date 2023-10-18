$(function(){
	//绑定事件:测试
	$("button").click(function(){
		let url=basePath+"user/info";
		//发送请求给后端
		$.get(url,function(result){
			console.log(result);
		});
	});
	
	//绑定菜单
	$("ul.nav.nav-pills.nav-sidebar.flex-column > li > ul> li").click(function(){
		// console.log(this);
		let id = $(this).attr("id");
		//使用ajax加载页面
		if(id){
			// 在页面的容器中加载代码片段
			$(".content > .content-fluid > .row").load(id+".html");
		}
	});
})