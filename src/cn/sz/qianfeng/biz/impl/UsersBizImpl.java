package cn.sz.qianfeng.biz.impl;

import cn.sz.qianfeng.biz.IUsersBiz;
import cn.sz.qianfeng.dao.IUsersDAO;
import cn.sz.qianfeng.dao.impl.UsersDAOImpl;
import cn.sz.qianfeng.vo.Users;

public class UsersBizImpl implements IUsersBiz {

	private IUsersDAO usersDAOImpl = new UsersDAOImpl();
	
	@Override
	public boolean doCreate(Users vo) {
		vo.setIsAdmin("1");
		return usersDAOImpl.doCreate(vo);
	}

	@Override
	public Users islogin(String loginname, String pwd) {
		return usersDAOImpl.islogin(loginname, pwd);
	}

}
