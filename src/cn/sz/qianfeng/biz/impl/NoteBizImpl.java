package cn.sz.qianfeng.biz.impl;

import java.util.List;

import cn.sz.qianfeng.biz.INoteBiz;
import cn.sz.qianfeng.dao.INoteDAO;
import cn.sz.qianfeng.dao.impl.NoteDAOImpl;
import cn.sz.qianfeng.factory.DAOFactory;
import cn.sz.qianfeng.vo.Note;

public class NoteBizImpl implements INoteBiz {

	private INoteDAO notedao = DAOFactory.getInstance().getNoteDAOInstance();
	
	@Override
	public boolean doCreate(Note vo) {
		return notedao.doCreate(vo);
	}

	@Override
	public boolean doUpdate(Note vo) {
		return notedao.doUpdate(vo);
	}

	@Override
	public boolean doRemove(Integer id) {
		return notedao.doRemove(id);
	}

	@Override
	public List<Note> findall(String column, String kw, int cp, int ps) {
		return notedao.findall(column, kw, cp, ps);
	}

	@Override
	public int getCount(String column, String kw) {
		return notedao.getCount(column, kw);
	}

	@Override
	public Note findById(Integer id) {
		return notedao.findById(id);
	}

}
