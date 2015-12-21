package cn.sz.qianfeng.dao;

import java.util.List;

public interface IDAO<T,K> {

	public boolean doCreate(T vo);
	
	public boolean doUpdate(T vo);
	
	public boolean doRemove(K id);
	
	public List<T> findall();
	
	public List<T> findall(int cp,int ps);
	
	public int getCount();
	
	public T findById(K id);
}
