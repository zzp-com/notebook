$(function(){
	sendcode();
	
	resetpwd();
	
	resetSubmit();
});
//提交重设密码函数
function resetSubmit(){
	$("#submit-reset").click(function(){
		var pwd=$("input[name='password']").val();
		var confirm=$("input[name='confirm']").val();
		var email=$("#field-phone").val();
		$("#errorpwd").show();
		$("#errorpwdsure").show();
		//密码
		if(pwd ==null || pwd ==""){
			$("input[name='password']").css("border", "1px solid red");
			$("#errorpwd").html("<span>密码不能为空</span>");
		}else if(pwd.length>20 || pwd.length<6){
			$("input[name='password']").css("border", "1px solid red");
			$("#errorpwd").html("<span>密码长度有误</span>");
		}else{
			$("input[name='password']").css("border", "1px solid #999999");
			$("#errorpwd").html("");
		}
		//确认密码
		if(confirm ==null || confirm ==""){
			$("input[name='confirm']").css("border", "1px solid red");
			$("#errorpwdsure").html("<span>确认密码不能为空</span>");
		}else if(pwd !=confirm){
			$("input[name='confirm']").css("border", "1px solid red");
			$("#errorpwdsure").html("<span>两个密码不一致</span>");
		}else{
			$("input[name='confirm']").css("border", "1px solid #999999");
			$("#errorpwdsure").html("");
			//进行提交数据
			if(setemail(email)){
				sendData(email,confirm);
			}
			
		}
		
	});
	
}

//发送提交数据函数
function sendData(email,confirm){
	$.ajax({
		url:path+"/jianshu/verificationController/resetpwd.do",
		type:"post",
		data:{"email":email,"pwd":confirm},
		dataType:"json",
		success:function(data){
			//返回数据进行提示
			if(data.status=="0"){
				alert(data.msg);
			}
			if(data.status=="1"){
				if(confirm(data.msg+"点击确定返回登录")){
					window.location.href=path+"/login.html";
				}
				
			}
			
			
		},
		error:function(){
			alert("修改失败");
		}
		
	});
}


//重置密码函数
function resetpwd(){
$("#submit-request").click(function(){
	var email=$("#field-phone").val();
		setemail(email);
	var code=$("#code").val();
	$("#errorcode").show();
	//判断验证码
	if(code==null || code == ""){
		$("#errorcode").html("<span>验证码不能为空</span>");
		$("#code").css("border", "1px solid red");
	}else if(!(/^[a-zA-Z\d]{6}$/).test(code) || code!=resultCode){
		$("#errorcode").html("<span>验证码不正确</span>");
		$("#code").css("border", "1px solid red");
	}else{
		$("#code").css("border", "1px solid #999999");
		$("#errorcode").html("");
		$(".reset-fields").show();
		
	}
	
	
	
	});
}




//找回密码发送验证码
function sendcode(){
	var flag=true;//用于控制重复点击发送验证码
	$("#fetch-code").click(function(){
		var email=$("#field-phone").val();
		if(setemail(email)){
			if(flag){
				flag=false;
			$.ajax({
				url:path+"/jianshu/verificationController/code.do",
				type:"post",
				data:{"email":email},
				dataType:"json",
				success:function(data){
					$("#errorcode").show();
					if(data.status === "-1"){
						$("#errorcode").html("<span>"+data.msg+"</span>");
					}
					if(data.status ==="1"){
						resultCode=data.resultCode;
						$("#errorcode").html("<span>"+data.msg+"</span>");
						var i=60;
						var id=setInterval(function(){ 
							if(i==-1){
								flag=true;
								clearInterval(id);
								$("#errorcode").html("<span>验证码已失效</span>");
							}
							if(i==59){
								$("#errorcode").html("<span>60秒后失效</span>");
							}
								i--;
						}, 1000);
					}
					
				},
				error:function(){
					flag=true;
					alert("发送失败");
				}
				
			});
			}
		}
		


		
	});
}

function setemail(email){
	// 邮箱验证
	var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
	$("#erroremail").show();
	// 判断邮箱
	if(email==null || email == ""){
		
		$("#erroremail").html("<span>邮箱不能为空</span>");
		$("#field-phone").css("border", "1px solid red");
		return false;
	} else if(!(reg.test(email))){
		$("#erroremail").html("<span>邮箱不正确</span>");
		$("#field-phone").css("border", "1px solid red");
		return false;
	}else{
		$("#field-phone").css("border", "1px solid #999999");
		$("#erroremail").html("");
		return true;
	}
}