package com.cunzhi.manager;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import com.cunzhi.dao.UserDao;
import com.cunzhi.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 用户注册类
 * @author Administrator
 *
 */
@Service
public class UserManagerImpl implements UserManager {
	@Resource
	private UserDao userDao;
	
	//返回-1为注册失败，返回1为注册成功
	public int AddUser(User user) {
		String email=user.getEmail();
		if(userDao.findUser(email) != null) {
			return -1;
		}
		
		Date now=new Date();
		user.setRegTime(now);
		userDao.saveUser(user);
		return 1;
	}
	
	public int Login(String infos,HttpServletRequest request) {
		JSONArray json=JSONArray.fromObject(infos);
		String email="";
		String pwd="";
		for(int a=0;a<json.size();a++) {
			JSONObject info = (JSONObject) json.getJSONObject(a);
			email=info.getString("email").trim();
			pwd=info.getString("pwd").trim();
		}
		if(userDao.findUser(email)==null) {
			return 0;//返回0代表此邮箱未被注册
		}
		User user=userDao.findLogin(email, pwd);
		if(user==null) {
			return -1;//返回-1代表密码错误
		}
		HttpSession session = request.getSession(); 
		session.setAttribute("email", email);

		return 1;//返回1代表登录成功
		
	}

	public int resetpwd(String email, String pwd) {
		if(userDao.findUser(email)==null) {
			return 0;//返回0代表此邮箱未被注册
		}
		userDao.resetpwd(email, pwd);
		return 1;//返回1代表成功
	}
	

}
