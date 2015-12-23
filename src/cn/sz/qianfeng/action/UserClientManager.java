package cn.sz.qianfeng.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

import cn.sz.qianfeng.factory.ServiceFactory;
import cn.sz.qianfeng.vo.Directory;
import cn.sz.qianfeng.vo.Users;

public class UserClientManager {

	/**
	 * 控制台接收用户名和密码，为登录验证准备
	 * @return
	 */
	public Users enterLoginInfo(){
		Scanner input = new Scanner(System.in);
		System.out.println("您选择的是【登录】");
		System.out.println("请输入用户名：");
		String loginname = input.next();
		System.out.println("请输入密码：");
		String pwd = input.next();

		Users users = new Users();
		users.setLoginname(loginname);
		users.setPwd(pwd);

		return users;
	}
	
	/**
	 * 接收所有用户信息，为用户注册准备
	 * @return
	 */
	public Users enterForReg(){
		Users users = null;
		try {
			Scanner input = new Scanner(System.in);
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
			System.out.println("请输入您的毕业学校：");
			String university = input.next();
			university = URLEncoder.encode(university, "utf-8");
			System.out.println("请输入您的专业：");
			String subject = input.next();
			subject = URLEncoder.encode(subject, "utf-8");
			System.out.println("请输入您的毕业时间(格式为2015-09):");
			String graduateTime = input.next();
			
			List<Directory> list = ServiceFactory.getDirectoryBizInstance().findall("area");
			System.out.println("请选择您的班级所在的区域");
			for (int i = 0; i < list.size(); i++) {
				Directory direct = list.get(i);
				String d = URLDecoder.decode(direct.getOption(), "utf-8");
				System.out.println("           "+i+"."+d);
			}
			Directory directory = list.get(input.nextInt());
			
			List<Directory> sublist = ServiceFactory.getDirectoryBizInstance().findall("subject");
			System.out.println("请选择您的课程：");
			for (int i = 0; i < sublist.size(); i++) {
				Directory direct = sublist.get(i);
				System.out.println("           "+i+"."+direct.getOption());
			}
			Directory subDirect = list.get(input.nextInt());
			
			System.out.println("请输入班级编号：");
			String cls = input.next();
			cls = directory.getKw()+"-"+subDirect.getKw()+"-"+cls;

			users = new Users();
			users.setLoginname(loginname);
			users.setPwd(pwd);
			users.setRealname(realname);
			users.setNickname(nickname);
			users.setMobile(mobile);
			users.setEmail(email);
			users.setUniversity(university);
			users.setSubject(subject);
			users.setGraduateTime(graduateTime);
			users.setCls(cls);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	/**
	 * 控制台接收原密码并验证，如果一致，再接收新密码并确认
	 * @param user
	 * @return 返回的对象中存放了userid和pwd,如果返回null,说明用户放弃修改密码
	 */
	public Users enterForChangePwd(Users user){
		Scanner input = new Scanner(System.in);
		Users users = null;
		System.out.println("您选择的是【修改密码】");
		String newpwd = null;
		boolean flag = true;
		while(flag){
			System.out.println("请输入您原来的密码：");
			String oldpwd = input.next();
			if(oldpwd!=null&&oldpwd.equals(user.getPwd())){
				System.out.println("请输入新密码：");
				newpwd = input.next();
				if(newpwd!=null&&!newpwd.equals("")){
					System.out.println("请确认您的新密码:");
					String repwd = input.next();
					if(repwd!=null&&repwd.equals(newpwd)){
						users = new Users();
						users.setPwd(newpwd);
						users.setUserid(user.getUserid());
						break;
					}else{
						System.out.println("两次密码不一致或密码不能为空，请重新输入");
					}
				}else{
					System.out.println("密码不能为空，请重新输入");
				}
			}else{
				System.out.println("您输入的原密码不正确，请选择(1.重新输入      2.退出):");
				flag = input.next().equals("1")?true:false;
			}
		}
		return users;
	}
	
	/**
	 * 显示用户的个人信息
	 * @param user
	 */
	public void showPersonalInfo(Users user){
		System.out.println("我的信息：");
		System.out.println("登录名："+user.getLoginname());
		System.out.println("真实姓名："+user.getRealname());
		System.out.println("昵称："+user.getNickname());
		System.out.println("手机："+user.getMobile());
		System.out.println("email:"+user.getEmail());
		System.out.println("毕业学校："+user.getUniversity());
		System.out.println("专业："+user.getSubject());
		System.out.println("毕业时间："+user.getGraduateTime());
		System.out.println("班级："+user.getCls());
	}
	
	
	
}
