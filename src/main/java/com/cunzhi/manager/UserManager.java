package com.cunzhi.manager;

import javax.servlet.http.HttpServletRequest;

import com.cunzhi.entity.User;
/*
 * 用户注册
 */
public interface UserManager {
	public int AddUser(User user);
	public int Login(String infos,HttpServletRequest request);
	public int resetpwd(String email,String pwd);
}
