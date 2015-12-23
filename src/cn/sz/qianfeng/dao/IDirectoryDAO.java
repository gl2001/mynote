package cn.sz.qianfeng.dao;

import java.util.List;

import cn.sz.qianfeng.vo.Directory;

public interface IDirectoryDAO extends IDAO<Directory, Integer> {

	public List<Directory> findall(String sub);
}
