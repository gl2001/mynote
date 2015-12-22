package cn.sz.qianfeng.dao;

import java.util.List;

import cn.sz.qianfeng.vo.Note;

public interface INoteDAO extends IDAO<Note, Integer> {

	/**
	 * ����ָ��������ҳ�����ʼǼ�¼
	 * @param column : ����
	 * @param kw : �йؼ���
	 * @param cp : ��ǰҳ�룬currentpage
	 * @param ps : ÿҳ��ʾ����,pagesize
	 * @return
	 */
	public List<Note> findall(String column,String kw,int cp,int ps);
	
	/**
	 * ͳ��ָ���е�����������Ϸ�ҳ��
	 * @param column : ����
	 * @param kw �� �йؼ���
	 * @return
	 */
	public int getCount(String column,String kw);
}
