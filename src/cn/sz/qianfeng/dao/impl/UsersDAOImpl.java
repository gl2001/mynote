package cn.sz.qianfeng.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.sz.qianfeng.dao.IUsersDAO;
import cn.sz.qianfeng.jdbc.DataBaseConnection;
import cn.sz.qianfeng.vo.Users;

public class UsersDAOImpl implements IUsersDAO {

	private DataBaseConnection dbc = new DataBaseConnection();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * 用户注册，这里只能注册为普通用户，管理员不能添加
	 */
	@Override
	public boolean doCreate(Users vo) {
		conn = dbc.getConnection();
		String sql = "insert into users(loginname,pwd,realname,nickname,mobile,email,isAdmin)" +
				" values(?,?,?,?,?,?,'1')";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getLoginname());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getRealname());
			pstmt.setString(4, vo.getNickname());
			pstmt.setInt(5, vo.getMobile());
			pstmt.setString(6, vo.getEmail());
			
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
	public boolean doUpdate(Users vo) {
		return false;
	}

	@Override
	public boolean doRemove(Integer id) {
		return false;
	}

	@Override
	public List<Users> findall() {
		return null;
	}

	@Override
	public List<Users> findall(int cp, int ps) {
		return null;
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Users findById(Integer id) {
		return null;
	}

	@Override
	public Users islogin(String loginname, String pwd) {
		Users vo = null;
		conn = dbc.getConnection();
		String sql = "select userid,loginname,pwd,realname,nickname,mobile,email,isAdmin " +
				"from users where loginname=? and pwd=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginname);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new Users();
				vo.setUserid(rs.getInt(1));
				vo.setLoginname(rs.getString(2));
				vo.setPwd(rs.getString(3));
				vo.setRealname(rs.getString(4));
				vo.setNickname(rs.getString(5));
				vo.setMobile(rs.getInt(6));
				vo.setEmail(rs.getString(7));
				vo.setIsAdmin(rs.getString(8));
			}
			return vo;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbc.close(conn, pstmt, rs);
		}
		return null;
	}

}
