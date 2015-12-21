package cn.sz.qianfeng.factory;

import cn.sz.qianfeng.dao.IUsersDAO;
import cn.sz.qianfeng.dao.impl.UsersDAOImpl;

public class DAOFactory {

	public static IUsersDAO getUsersDAOInstance(){
		return new UsersDAOImpl();
	}
}
