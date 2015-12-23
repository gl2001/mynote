package cn.sz.qianfeng.biz;

import java.util.List;

import cn.sz.qianfeng.vo.Directory;

public interface IDirectoryBiz {

	public List<Directory> findall(String sub);
}
