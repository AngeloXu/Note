/*登录界面JS程序*/
$(function(){
	//为登录按钮绑定单击
	$('#login').click(loginAction);
	$('#count').focus().blur(checkName);
	$('#password').blur(checkPassword);
	$('#regist_button').click(registAction);
	$('#regist_username').blur(checkRegistUsername);
	//$('#nickname').blur(checkNickname);
	$('#regist_password').blur(checkRegistPassword);
	$('#final_password').blur(checkFinalPassword);
});
	
function registAction(){
	console.log('registAction');
	var pass=checkRegistUsername()+checkRegistPassword()+checkFinalPassword();
	console.log('pass:'+pass);
	if(pass!=3){
		return;
	}
	var url='user/regist.do';
	var name=$('#regist_username').val();
	var nick=$('#nickname').val();
	var pwd=$('#regist_password').val();
	var confirm=$('#final_password').val();
	var data={'name':name,'nick':nick,'password':pwd,'confirm':confirm};
	$.post(url,data,function(result){
		if(result.state==0){
			var user=result.data;
			
			$('#back').click();
			console.log(user);
			$('#count').focus().val(user.name);
			console.log('0');
			$('#count-msg').empty();
			console.log('123');
			$('#password').focus();//光标放在密码框上
			console.log('456');
		}else if(result.state==2){
			$('#warning_1 span').html(result.message).parent().show();
		}else if(result.state==3){
			$('#warning_2 span').html(result.message).parent().show();
		}else{
			alert(result.message);
		}
		
	});
	
}
function checkRegistUsername(){
	console.log("checkRegistUsername");
	var name=$('#regist_username').val();
	console.log(name);
	var reg=/^\w{3,10}$/;
	if(!reg.test(name)){
		$('#warning_1 span').html('用户名不合规范').parent().show();
		return false;
	}
	$('#warning_1').hide();
	return true;
}
function checkRegistPassword(){
	console.log("checkRegistPassword");
	var password=$('#regist_password').val();
	var reg=/^\w{3,10}$/;
	if(!reg.test(password)){
		$('warning_2 span').html('密码不合规').parent().show();
		return false;
	}
	$('#warning_2').hide();
	return true;
	
}
function checkFinalPassword(){
	console.log("checkFinalPassword");
	var confirm=$('#final_password').val();
	var pwd=$('#regist_password').val();
	if(confirm!=pwd){
		$('#warning_3 span').html('确认密码不一致a');
		return false;
	}
	return true;	
}
//登录按钮的动作
function loginAction(){
	console.log('login click!');
	//收集用户名和密码数据
	var name=$('#count').val();
	var password=$('#password').val();
	//验证用户名和密码
	var pass=checkName()+checkPassword();
	if(pass!=2){
		return;
	}
	var parameter={'name':name,'password':password};
	//发送Ajax请求
	$.ajax({
		url:'user/login.do',
		data:parameter,
		dataType:'json',
		type:'post',
		success:function(result){
			//{state:0,data:,message}
			if(result.state==0){
				console.log("SUCCESS");
				console.log(result.data);
				var user=result.data;
				setCookie('userId',user.id);
				location.href='edit.html';
				return;
			}else if(result.state==2){//用户名错误
				$('#count-msg').html(result.message);
				return;
			}else if(result.state==3){//密码错误
				$('#password-msg').html(result.message);
				return;
			}
			alert(result.message);
		},
		error:function(){
			alert('Ajax请求失败');
		}
	
	});
	
}

function checkName(){
	var name=$('#count').val();
	if(name==null||name==""){
		 //提示错误
		$('#count-msg').html('用户名不能空');
		return false;
	}
	var reg=/^\w{3,10}$/;
	if(!reg.test(name)){
		$('#count-msg').html('用户名长度不对');
		return false;
	}
	$('#count-msg').empty();
	return true;
}
function checkPassword(){
	var password=$('#password').val();
	if(password==null||password==''){
		$('#password-msg').html('密码不能为空');
		return false;
	}
	var reg=/^\w{3,10}$/;
	if(!reg.test(password)){
		$('#password-msg').html("密码不合规范");
		return false;
	}
	$('#password-msg').empty();
	return true;
	
	
}