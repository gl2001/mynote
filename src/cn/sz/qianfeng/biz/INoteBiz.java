package cn.sz.qianfeng.biz;

import java.util.List;

import cn.sz.qianfeng.vo.Note;

public interface INoteBiz {

	/**
	 * ���һƪ�ʼ�
	 * @param vo
	 * @return
	 */
	public boolean doCreate(Note vo);
	
	/**
	 * �޸ıʼǼ�¼�����������ʼ�����
	 * @param vo
	 * @return
	 */
	public boolean doUpdate(Note vo);
	
	/**
	 * ɾ���ʼǼ�¼��������ɾ���ʼ�����
	 * @param id
	 * @return
	 */
	public boolean doRemove(Integer id);
	
	/**
	 * ����ĳһ������ѯ�ʼ�
	 * @param column : ����
	 * @param kw �� ������
	 * @param cp �� ��ǰҳ�룬currentPage
	 * @param ps : ÿҳ��ʾ����,pagesize
	 * @return
	 */
	public List<Note> findall(String column, String kw, int cp, int ps);
	
	/**
	 * ����ĳһ����ͳ�ƱʼǼ�¼��������
	 * @param column : ����
	 * @param kw �� ������
	 * @return
	 */
	public int getCount(String column, String kw);
	
	/**
	 * ���ݱʼ�id��ѯ�ʼǼ�¼����
	 * @param id
	 * @return
	 */
	public Note findById(Integer id);
}
