package cn.sz.qianfeng.biz.impl;

import java.util.List;

import cn.sz.qianfeng.biz.INoteBiz;
import cn.sz.qianfeng.dao.INoteDAO;
import cn.sz.qianfeng.dao.impl.NoteDAOImpl;
import cn.sz.qianfeng.vo.Note;

public class NoteBizImpl implements INoteBiz {

	private INoteDAO noteDAOImpl = new NoteDAOImpl();
	
	@Override
	public boolean doCreate(Note vo) {
		return noteDAOImpl.doCreate(vo);
	}

	@Override
	public boolean doUpdate(Note vo) {
		return noteDAOImpl.doUpdate(vo);
	}

	@Override
	public boolean doRemove(Integer id) {
		return noteDAOImpl.doRemove(id);
	}

	@Override
	public List<Note> findall(String column, String kw, int cp, int ps) {
		return noteDAOImpl.findall(column, kw, cp, ps);
	}

	@Override
	public int getCount(String column, String kw) {
		return noteDAOImpl.getCount(column, kw);
	}

}
