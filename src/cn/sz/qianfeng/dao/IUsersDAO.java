package cn.sz.qianfeng.dao;

import cn.sz.qianfeng.vo.Users;

public interface IUsersDAO extends IDAO<Users, Integer> {

	/**
	 * 用户登录
	 * @param loginname 名字
	 * @param pwd 密码
	 * @return 登录成功则返回一个对象，登录失败则返回null
	 */
	public Users islogin(String loginname,String pwd);
}
