$(function(){
	//注册表单提交事件
	$("#submit").click(function(){
		judge();
		
	});
	
	
});
function sendinfo(infos){
	//利用ajax向后台传值
	$.ajax({
		url:path+"/jianshu/verificationController/reg.do",
		type:"post",
		data:{"infos":JSON.stringify(infos)},//传递数组前得将数组转换成json数组
		dataType:"json",
		//传入数组必须加这句，用传统的方式来序列化数据
		success:function(data){
			if(data.status === 0){
				$("#errorcode").val(data.msg);
			}
			if(data.status === -1){
				alert(data.msg);
			}
			if(data.status === 1){
				if(confirm(data.msg+"点击确定返回登录")){
					window.location.href=path+"/login.html";
				}
			}
			if(data.status === 2){
				$("#errorcode").val(data.msg);
			}
			
		},
		error:function(){
			
			alert("注册失败");
		}
		
	});
}
//判断注册信息
function judge(){
	var infos=new Array();
	var o=new Object();
	var count=0;
	var name=$("#name").val();
	var email=$("#email").val();
	var code=$("#vercode").val();
	var pwd=$("#pwd").val();
	//邮箱验证
	var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
	
	
	//对用户输入的值进行判断
	if(name==null || name == ""){
		$("#errorname").val("昵称不能为空");
		$("#name").css("border", "1px solid red");
	}else{
		$("#name").css("border", "1px solid #999999");
		$("#errorname").val("");
		o.name=name;
		count++;
	}
	//判断邮箱
	if(email==null || email == ""){
		$("#erroremail").val("邮箱不能为空");
		$("#email").css("border", "1px solid red");
	} else if(!(reg.test(email))){
		$("#erroremail").val("邮箱不正确！");
		$("#email").css("border", "1px solid red");
	}else{
		$("#email").css("border", "1px solid #999999");
		$("#erroremail").val("");
		o.email=email;
		count++;
	}
	//判断验证码
	if(code==null || code == ""){
		$("#errorcode").val("验证码不能为空");
		$("#vercode").css("border", "1px solid red");
	}else if(!(/^[a-zA-Z\d]{6}$/).test(code)){
		$("#errorcode").val("验证码不正确");
		$("#vercode").css("border", "1px solid red");
	}else{
		$("#vercode").css("border", "1px solid #999999");
		$("#errorcode").val("");
		o.code=code;
		count++;
	}
	//判断密码
	if(pwd==null || pwd == ""){
		$("#errorpwd").val("密码不能为空");
		$("#pwd").css("border", "1px solid red");
	}else if(pwd.length>20 || pwd.length<6){
		$("#errorpwd").val("密码长度有误");
		$("#pwd").css("border", "1px solid red");
	}else{
		$("#pwd").css("border", "1px solid #999999");
		$("#errorpwd").val("");
		o.pwd=pwd;
		count++;
	}
	
	if(count == 4){
		infos.push(o);
		sendinfo(infos);
		$("#errorcode").val("");
	}
	
}
