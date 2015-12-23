package cn.sz.qianfeng.biz.impl;

import java.util.List;

import cn.sz.qianfeng.biz.IDirectoryBiz;
import cn.sz.qianfeng.dao.IDirectoryDAO;
import cn.sz.qianfeng.dao.impl.DirectoryDAOImpl;
import cn.sz.qianfeng.factory.DAOFactory;
import cn.sz.qianfeng.vo.Directory;

public class DirectoryBizImpl implements IDirectoryBiz {

	@Override
	public List<Directory> findall(String sub) {
		return DAOFactory.getDirectoryDAOInstance().findall(sub);
	}

}
