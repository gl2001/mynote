package cn.sz.qianfeng.biz;

import cn.sz.qianfeng.vo.Users;

public interface IUsersBiz {

	/**
	 * �����ͨ�û���ע�Ṧ�ܣ�����ע�����Ա
	 * @param vo
	 * @return
	 */
	public boolean doCreate(Users vo);
	
	/**
	 * �û���¼����ͨ�û������Ա��ͨ����������ɵ�¼
	 * @param loginname
	 * @param pwd
	 * @return
	 */
	public Users islogin(String loginname, String pwd);
}
