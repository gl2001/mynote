package cn.sz.qianfeng.biz;

import java.util.List;

import cn.sz.qianfeng.vo.Note;

public interface INoteBiz {

	/**
	 * 添加一篇笔记
	 * @param vo
	 * @return
	 */
	public boolean doCreate(Note vo);
	
	/**
	 * 修改笔记记录，但不包括笔记正文
	 * @param vo
	 * @return
	 */
	public boolean doUpdate(Note vo);
	
	/**
	 * 删除笔记记录，但不能删除笔记正文
	 * @param id
	 * @return
	 */
	public boolean doRemove(Integer id);
	
	/**
	 * 根据某一列来查询笔记
	 * @param column : 列名
	 * @param kw ： 列内容
	 * @param cp ： 当前页码，currentPage
	 * @param ps : 每页显示行数,pagesize
	 * @return
	 */
	public List<Note> findall(String column, String kw, int cp, int ps);
	
	/**
	 * 根据某一列来统计笔记记录的总行数
	 * @param column : 列名
	 * @param kw ： 列内容
	 * @return
	 */
	public int getCount(String column, String kw);
	
	/**
	 * 根据笔记id查询笔记记录对象
	 * @param id
	 * @return
	 */
	public Note findById(Integer id);
}
