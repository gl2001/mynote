package cn.sz.qianfeng.dao;

import java.util.List;

import cn.sz.qianfeng.vo.Note;

public interface INoteDAO extends IDAO<Note, Integer> {

	/**
	 * 根据指定列来分页搜索笔记记录
	 * @param column : 列名
	 * @param kw : 列关键字
	 * @param cp : 当前页码，currentpage
	 * @param ps : 每页显示行数,pagesize
	 * @return
	 */
	public List<Note> findall(String column,String kw,int cp,int ps);
	
	/**
	 * 统计指定列的总行数，配合分页用
	 * @param column : 列名
	 * @param kw ： 列关键字
	 * @return
	 */
	public int getCount(String column,String kw);
}
