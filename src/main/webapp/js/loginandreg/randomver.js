$(function(){
	var flag=true;//用于控制重复点击发送验证码
	//实现邮箱验证码发送
	$("#code").click(function(){
		var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
		var email=$("#email").val();
		if(email==null || email == ""){
			$("#erroremail").val("邮箱不能为空");
		} else if(!(reg.test(email))){
			$("#erroremail").val("邮箱不正确！");
		}else{
			$("#erroremail").val("");
			if(flag){
				flag=false;
			//利用ajax传递数据
			$.ajax({
				url:path+"/jianshu/verificationController/code.do",
				type:"post",
				data:{"email":email},
				dataType:"json",
				success:function(data){
					//做一个倒计时
					if(data.status == "-1"){
						$("#errorcode").val(data.msg);
					}
					if(data.status == "1"){
						$("#errorcode").val(data.msg);
						var i=60;
						var id=setInterval(function(){ 
							if(i==-1){
								clearInterval(id);
								flag=true;
								$("#errorcode").val("验证码已失效");
							}
							if(i==59){
								$("#errorcode").val("60秒后失效");
							}
								i--;
						}, 1000);
					}
					
					
				},
				error:function(){
					flag=true;
					alert("发送验证码失败");
				}
				
			});
			}
		}
		
	});
});


