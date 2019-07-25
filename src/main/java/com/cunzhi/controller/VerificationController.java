package com.cunzhi.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cunzhi.entity.User;
import com.cunzhi.manager.UserManager;
import com.cunzhi.manager.VerificationManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * 邮箱验证
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/jianshu/verificationController")
public class VerificationController {
		@Resource
		private VerificationManager verManager;
		
		@Resource
		private UserManager userManager;
		
		@RequestMapping("/code.do")
		@ResponseBody
		public Map<String,Object> randomCode(HttpServletRequest request,
				HttpServletResponse response) {
			String email=request.getParameter("email");
			String resultCode=verManager.sendEamilCode(email);
			System.out.println(resultCode);
			HttpSession session = request.getSession(); 
			session.setAttribute("resultCode", resultCode);
			session.setMaxInactiveInterval(60);
			Map<String,Object> map=new HashMap<String, Object>();
			//默认0是错误，-1是失败，1是成功
			if(resultCode ==null || "".equals(resultCode)) {
				map.put("status", "-1");
				map.put("msg", "发送验证码失败");
				return map;
			}
			map.put("status", "1");
			map.put("msg", "发送验证码成功");
			map.put("resultCode", resultCode);
			return map;
			
		}
		
		@RequestMapping("/reg.do")
		@ResponseBody
		public Map<String,Object> regUser(HttpServletRequest request,
				HttpServletResponse response){
			Map<String,Object> map=new HashMap<String, Object>();
				String infos=request.getParameter("infos");
				JSONArray json=JSONArray.fromObject(infos);
				User user=new User();
				String code="";
				for(int a=0;a<json.size();a++) {
					JSONObject info = (JSONObject) json.getJSONObject(a);
					user.setName(info.getString("name"));
					user.setEmail(info.getString("email"));
					user.setPassword(info.getString("pwd"));
					code=info.getString("code");
				}
				HttpSession session = request.getSession(); 
				String backcode=(String) session.getAttribute("resultCode");
				//System.out.println(backcode+" : "+code);
				//默认0是错误，-1是失败，1是成功
				if(backcode==null) {
					map.put("status", 2);
					map.put("msg", "验证码已失效");
					return map;
				}
				if(!code.trim().equals(backcode.trim()) ) {
					map.put("status", 0);
					map.put("msg", "验证码错误");
					return map;
				}
				if(userManager.AddUser(user) == -1) {
					map.put("status", -1);
					map.put("msg", "此邮箱已被注册");
					return map;
				}
				map.put("status", 1);
				map.put("msg", "注册成功");
				return map;
			
		}
		
		@RequestMapping("/login.do")
		@ResponseBody
		public Map<String,Object> login(HttpServletRequest request,
				HttpServletResponse response){
				Map<String,Object> map=new HashMap<String, Object>();
				String infos=request.getParameter("infos");
				int info=userManager.Login(infos,request);
				if(info==0) {
					map.put("msg", "邮箱未注册");
					map.put("status", "0");
					return map;
				}
				if(info==-1) {
					map.put("msg", "密码错误");
					map.put("status", "-1");
					return map;
				}
				map.put("msg", "登录成功");
				map.put("status", "1");
				return map;
			
		}
		
		@RequestMapping("/resetpwd.do")
		@ResponseBody
		public Map<String,Object> resetpwd(HttpServletRequest request,
				HttpServletResponse response){
			Map<String,Object> map=new HashMap<String, Object>();
				String email=request.getParameter("email");
				String pwd=request.getParameter("pwd");
				int i=userManager.resetpwd(email, pwd);
				if(i==0) {
					map.put("status", "0");
					map.put("msg", "此邮箱未注册");
					return map;
				}
				map.put("status", "1");
				map.put("msg", "修改密码成功");
				return map;
		
		}
		
}
