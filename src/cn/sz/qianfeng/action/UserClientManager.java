package cn.sz.qianfeng.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;

import cn.sz.qianfeng.vo.Users;

public class UserClientManager {

	public Users enterLoginInfo(){
		Scanner input = new Scanner(System.in);
		System.out.println("您选择的是登录");
		System.out.println("请输入用户名：");
		String loginname = input.next();
		System.out.println("请输入密码：");
		String pwd = input.next();

		Users users = new Users();
		users.setLoginname(loginname);
		users.setPwd(pwd);

		return users;
	}
	
	public Users enterForReg(){
		Users users = null;
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("您选择的是登录");
			System.out.println("请输入用户名：");
			String loginname = input.next();
			System.out.println("请输入密码：");
			String pwd = input.next();
			System.out.println("请输入真实姓名:");
			String realname = input.next();
			realname = URLEncoder.encode(realname, "utf-8");
			System.out.println("请输入您的昵称：");
			String nickname = input.next();
			nickname = URLEncoder.encode(nickname, "utf-8");
			System.out.println("请输入您的手机号码:");
			String mobile = input.next();
			System.out.println("请输入您的email：");
			String email = input.next();

			users = new Users();
			users.setLoginname(loginname);
			users.setPwd(pwd);
			users.setRealname(realname);
			users.setNickname(nickname);
			users.setMobile(mobile);
			users.setEmail(email);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return users;
	}
	
}
