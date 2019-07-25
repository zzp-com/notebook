package com.jianshu.testdb;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConntion {
	@Test
	public ApplicationContext getContext() {
		String [] conf= {"springmybatis.xml","springmvc.xml"};
		ApplicationContext ctx=new ClassPathXmlApplicationContext("springmybatis.xml");
		System.out.println(ctx);
		return ctx;
		
	}
	@Test 
	public void getDate() {
		
	}
	
}
