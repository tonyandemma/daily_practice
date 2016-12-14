package com.java.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class ShiroDemo {

	private static final transient Logger log = LoggerFactory.getLogger(ShiroDemo.class);
	
	public static void main(String[] args) {
		log.info("My First Apach Shiro Application");
		//读取配置文件，初始化SecurityManager工厂
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:jdbc_realm.ini");
		//获取securityManager实例
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		//把SecurityManager实例修绑定到securityUtils
		SecurityUtils.setSecurityManager(securityManager);
		//得到当前执行的用户
		Subject currentUser = SecurityUtils.getSubject();
		//创建token令牌，用户名/密码
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","1234");
		//身份认证
		try {
			currentUser.login(token);
			System.out.println("身份认证成功");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			System.out.println("身份认证失败");
		}
		//退出
		currentUser.logout();
	}
}
