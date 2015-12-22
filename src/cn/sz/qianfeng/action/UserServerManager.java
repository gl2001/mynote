package cn.sz.qianfeng.action;

import cn.sz.qianfeng.factory.ServiceFactory;
import cn.sz.qianfeng.vo.Users;

public class UserServerManager {
	
	public Users checkLogin(Users users){
		if(users==null){
			return null;
		}
		//ȥ���ݿ���֤���û��Ƿ����
		Users user = ServiceFactory.getUsersBizInstance().islogin(users.getLoginname(), users.getPwd());
		if(user!=null){
			return user;
		}
		return null;
	}
	
	/**
	 * ����û�ע�Ṧ��
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
