package cn.sz.qianfeng.biz;

import cn.sz.qianfeng.vo.Users;

public interface IUsersBiz {

	/**
	 * 完成普通用户的注册功能，不能注册管理员
	 * @param vo
	 * @return
	 */
	public boolean doCreate(Users vo);
	
	/**
	 * 用户登录，普通用户与管理员都通过本方法完成登录
	 * @param loginname
	 * @param pwd
	 * @return
	 */
	public Users islogin(String loginname, String pwd);
	
	/**
	 * 修改密码
	 * @param vo:该对象中需要传递密码和userid,其他信息的修改不能调用该方法
	 * @return
	 */
	public boolean doUpdate(Users vo);
}
