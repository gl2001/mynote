package cn.sz.qianfeng.biz.impl;

import java.util.List;

import cn.sz.qianfeng.biz.INoteBiz;
import cn.sz.qianfeng.dao.INoteDAO;
import cn.sz.qianfeng.dao.impl.NoteDAOImpl;
import cn.sz.qianfeng.factory.DAOFactory;
import cn.sz.qianfeng.vo.Note;

public class NoteBizImpl implements INoteBiz {

	@Override
	public boolean doCreate(Note vo) {
		return DAOFactory.getNoteDAOInstance().doCreate(vo);
	}

	@Override
	public boolean doUpdate(Note vo) {
		return DAOFactory.getNoteDAOInstance().doUpdate(vo);
	}

	@Override
	public boolean doRemove(Integer id) {
		return DAOFactory.getNoteDAOInstance().doRemove(id);
	}

	@Override
	public List<Note> findall(String column, String kw, int cp, int ps) {
		return DAOFactory.getNoteDAOInstance().findall(column, kw, cp, ps);
	}

	@Override
	public int getCount(String column, String kw) {
		return DAOFactory.getNoteDAOInstance().getCount(column, kw);
	}

}
