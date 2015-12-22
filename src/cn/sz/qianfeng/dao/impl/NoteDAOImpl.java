package cn.sz.qianfeng.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.sz.qianfeng.dao.INoteDAO;
import cn.sz.qianfeng.jdbc.DataBaseConnection;
import cn.sz.qianfeng.vo.Note;

public class NoteDAOImpl implements INoteDAO {

	private DataBaseConnection dbc = new DataBaseConnection();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	@Override
	public boolean doCreate(Note vo) {
		conn = dbc.getConnection();
		String sql = "insert into note(userid,notename,createtime,path,isShare,summary,readTimes,likeTimes)" +
				" values(?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getUserid());
			pstmt.setString(2, vo.getNotename());
			pstmt.setDate(3, new java.sql.Date(vo.getCreateTime().getTime()));
			pstmt.setString(4, vo.getPath());
			pstmt.setString(5, vo.getIsShare());
			pstmt.setString(6, vo.getSummary());
			pstmt.setInt(7, vo.getReadTimes());
			pstmt.setInt(8, vo.getLikeTimes());
			
			int result = pstmt.executeUpdate();
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbc.close(conn, pstmt, rs);
		}
		return false;
	}

	@Override
	public boolean doUpdate(Note vo) {
		conn = dbc.getConnection();
		String sql = "update note set userid=?,notename=?,createtime=?,path=?,isShare=?," +
				"summary=?,readTimes=?,likeTimes=? where nid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getUserid());
			pstmt.setString(2, vo.getNotename());
			pstmt.setDate(3, new java.sql.Date(vo.getCreateTime().getTime()));
			pstmt.setString(4, vo.getPath());
			pstmt.setString(5, vo.getIsShare());
			pstmt.setString(6, vo.getSummary());
			pstmt.setInt(7, vo.getReadTimes());
			pstmt.setInt(8, vo.getLikeTimes());
			pstmt.setInt(9, vo.getNid());
			
			int result = pstmt.executeUpdate();
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbc.close(conn, pstmt, rs);
		}
		return false;
	}

	@Override
	public boolean doRemove(Integer id) {
		conn = dbc.getConnection();
		String sql = "delete from note where nid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			int result = pstmt.executeUpdate();
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbc.close(conn, pstmt, rs);
		}
		return false;
	}

	@Override
	public List<Note> findall() {
		return null;
	}

	@Override
	public List<Note> findall(int cp, int ps) {
		return null;
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Note findById(Integer id) {
		return null;
	}

	@Override
	public List<Note> findall(String column, String kw, int cp, int ps) {
		conn = dbc.getConnection();
		List<Note> all = new ArrayList<Note>();
		Note vo = null;
		String sql = "select nid,userid,notename,createtime,path,isShare,summary,readTimes," +
				"likeTimes from note where "+column+" =? limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kw);
			pstmt.setInt(2, (cp-1)*ps);
			pstmt.setInt(3, ps);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new Note();
				vo.setNid(rs.getInt(1));
				vo.setUserid(rs.getInt(2));
				vo.setNotename(rs.getString(3));
				vo.setCreateTime(new Timestamp(rs.getDate(4).getTime()));
				vo.setPath(rs.getString(5));
				vo.setIsShare(rs.getString(6));
				vo.setSummary(rs.getString(7));
				vo.setReadTimes(rs.getInt(8));
				vo.setLikeTimes(rs.getInt(9));
				all.add(vo);
			}
			
			return all;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbc.close(conn, pstmt, rs);
		}
		return null;
	}

	@Override
	public int getCount(String column, String kw) {
		conn = dbc.getConnection();
		String sql = "select count(nid) from note where "+column+" =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kw);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbc.close(conn, pstmt, rs);
		}
		
		return 0;
	}

}
