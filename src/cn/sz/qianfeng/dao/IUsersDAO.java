package cn.sz.qianfeng.dao;

import cn.sz.qianfeng.vo.Users;

public interface IUsersDAO extends IDAO<Users, Integer> {

	/**
	 * �û���¼
	 * @param loginname ����
	 * @param pwd ����
	 * @return ��¼�ɹ��򷵻�һ�����󣬵�¼ʧ���򷵻�null
	 */
	public Users islogin(String loginname,String pwd);
}
