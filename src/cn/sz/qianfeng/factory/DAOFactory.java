package cn.sz.qianfeng.factory;

import cn.sz.qianfeng.dao.IDirectoryDAO;
import cn.sz.qianfeng.dao.INoteDAO;
import cn.sz.qianfeng.dao.IUsersDAO;
import cn.sz.qianfeng.dao.impl.DirectoryDAOImpl;
import cn.sz.qianfeng.dao.impl.NoteDAOImpl;
import cn.sz.qianfeng.dao.impl.UsersDAOImpl;

public class DAOFactory {
	
	private static DAOFactory FACTORY;
	
	private DAOFactory(){
		
	}
	
	public static DAOFactory getInstance(){
		if(FACTORY==null){
			FACTORY = new DAOFactory();
		}
		return FACTORY;
	}

	public IUsersDAO getUsersDAOInstance(){
		return new UsersDAOImpl();
	}
	
	public INoteDAO getNoteDAOInstance(){
		return new NoteDAOImpl();
	}
	
	public IDirectoryDAO getDirectoryDAOInstance(){
		return new DirectoryDAOImpl();
	}
}
