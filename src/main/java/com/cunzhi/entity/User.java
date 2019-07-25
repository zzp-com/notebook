package com.cunzhi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 参照jianshu_user表定义
 * 属性名和字段名保持一致
 * 属性类型与字段类型一样
 * 实现序列化
 * @author zzp
 *
 */
public class User implements Serializable{
		
		private String Name;
		private String Email;
		private String Password;
		private Date RegTime;
		
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getEmail() {
			return Email;
		}
		public void setEmail(String email) {
			Email = email;
		}
		public String getPassword() {
			return Password;
		}
		public void setPassword(String password) {
			Password = password;
		}
		public Date getRegTime() {
			return RegTime;
		}
		public void setRegTime(Date regTime) {
			RegTime = regTime;
		}
		
		
}
