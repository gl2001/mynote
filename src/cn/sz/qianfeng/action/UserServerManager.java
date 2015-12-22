package cn.sz.qianfeng.action;

import cn.sz.qianfeng.factory.ServiceFactory;
import cn.sz.qianfeng.vo.Users;

public class UserServerManager {
	
	public Users checkLogin(Users users){
		if(users==null){
			return null;
		}
		//去数据库验证该用户是否存在
		Users user = ServiceFactory.getUsersBizInstance().islogin(users.getLoginname(), users.getPwd());
		if(user!=null){
			return user;
		}
		return null;
	}
	
	/**
	 * 完成用户注册功能
	 * @param users
	 * @return
	 */
	public boolean isreg(Users users){
		if(users==null){
			return false;
		}
		return ServiceFactory.getUsersBizInstance().doCreate(users);
	}
}
