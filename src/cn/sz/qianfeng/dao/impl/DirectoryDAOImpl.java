package cn.sz.qianfeng.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.sz.qianfeng.dao.IDirectoryDAO;
import cn.sz.qianfeng.jdbc.DataBaseConnection;
import cn.sz.qianfeng.vo.Directory;

public class DirectoryDAOImpl implements IDirectoryDAO {

	private DataBaseConnection dbc = new DataBaseConnection();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	@Override
	public boolean doCreate(Directory vo) {
		return false;
	}

	@Override
	public boolean doUpdate(Directory vo) {
		return false;
	}

	@Override
	public boolean doRemove(Integer id) {
		return false;
	}

	@Override
	public List<Directory> findall() {
		conn = dbc.getConnection();
		List<Directory> all = new ArrayList<Directory>();
		Directory vo = null;
		String sql = "select directid,subject,option,kw from directory";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new Directory();
				vo.setDirectid(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setOption(rs.getString(3));
				vo.setKw(rs.getString(4));
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
	public List<Directory> findall(int cp, int ps) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Directory findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Directory> findall(String sub) {
		conn = dbc.getConnection();
		List<Directory> all = new ArrayList<Directory>();
		Directory vo = null;
		String sql = "select directid,subject,option,kw from directory where subject=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sub);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new Directory();
				vo.setDirectid(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setOption(rs.getString(3));
				vo.setKw(rs.getString(4));
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
}
