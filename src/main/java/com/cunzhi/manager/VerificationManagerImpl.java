package com.cunzhi.manager;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

/**
 * 验证类
 * @author Administrator
 *
 */
@Service
public class VerificationManagerImpl implements VerificationManager{
	//邮箱验证	
	public String randomCode() {
		//随机验证码
		String[] beforeShuffle= new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
				"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
				"b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z" };
		List list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
		sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(3, 9);
		return result;
		}
	
	public String sendEamilCode(String email) {
		HtmlEmail send = new HtmlEmail();
		// 获取随机验证码
		String resultCode = randomCode(); 
		try {
			send.setHostName("smtp.qq.com"); 
			send.setSmtpPort(465); //端口号
			send.setSSLOnConnect(true); //开启SSL加密
			send.setCharset("utf-8");
			send.addTo(email);//接收者的QQEamil
			send.setFrom("1814207915@qq.com", "ZZ");//第一个参数是发送者的QQEamil   第二个参数是发送者QQ昵称
			send.setAuthentication("1814207915@qq.com", "bccprcudgitpbdie"); //第一个参数是发送者的QQEamil   第二个参数是刚刚获取的授权码
			send.setSubject(checkAmorPm() + ",注册验证码"); //Eamil的标题  第一个参数是我写的判断上下午，删掉即可
			send.setMsg("HelloWorld!欢迎光临，注册验证码:   " + resultCode + "   请签收,有效期一分钟");//Eamil的内容
			send.send();//发送
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultCode;
		
	}
	//验证当前时间
	public String checkAmorPm() {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if(hour>=19 && hour<=23||hour>=0 && hour<=5) {
			return "晚上好";
		}else if(hour>=6 && hour<=11) {
			return "早上好";
		}else if(hour==12) {
			return "中午好";
		}else if(hour>=13 && hour<18) {
			return "下午好";
		}
		return "";
	}
	
}
