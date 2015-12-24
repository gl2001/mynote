package cn.sz.qianfeng.biz.impl;

import cn.sz.qianfeng.biz.IUsersBiz;
import cn.sz.qianfeng.dao.IUsersDAO;
import cn.sz.qianfeng.dao.impl.UsersDAOImpl;
import cn.sz.qianfeng.factory.DAOFactory;
import cn.sz.qianfeng.vo.Users;

public class UsersBizImpl implements IUsersBiz {

	private IUsersDAO userdao = DAOFactory.getInstance().getUsersDAOInstance();
	@Override
	public boolean doCreate(Users vo) {
		vo.setIsAdmin("1");
		return userdao.doCreate(vo);
	}

	@Override
	public Users islogin(String loginname, String pwd) {
		return userdao.islogin(loginname, pwd);
	}

	@Override
	public boolean doUpdate(Users vo) {
		return userdao.doUpdate(vo);
	}

	@Override
	public Users findById(Integer id) {
		return userdao.findById(id);
	}

}
