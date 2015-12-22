package cn.sz.qianfeng.factory;

import cn.sz.qianfeng.biz.INoteBiz;
import cn.sz.qianfeng.biz.IUsersBiz;
import cn.sz.qianfeng.biz.impl.NoteBizImpl;
import cn.sz.qianfeng.biz.impl.UsersBizImpl;

public class ServiceFactory {

	public static IUsersBiz getUsersBizInstance(){
		return new UsersBizImpl();
	}
	
	public static INoteBiz getNoteBizInstance(){
		return new NoteBizImpl();
	}
}
