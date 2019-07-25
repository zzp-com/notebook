package com.cunzhi.dao;

import com.cunzhi.entity.User;

public interface UserDao {
	public int saveUser(User user);
	public User findUser(String email);
	public User findLogin(String email,String pwd);
	public int resetpwd(String email,String pwd);
}
