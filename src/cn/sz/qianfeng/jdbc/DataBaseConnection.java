package cn.sz.qianfeng.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

	private static final String FILENAME = "myconn.properties";//���ݿ����Ӳ�������ڸ��ļ���
	private static Properties properties = new Properties();//����װ�����ݿ����Ӳ���
	private static InputStream is = null;
	static{
		//��src�¶�ȡproperties�ļ�������
		is = DataBaseConnection.class.getClassLoader().getResourceAsStream(FILENAME);
		try {
			//����һ����ȡ�����ݴ���properties������,���������������ʱ��ȡ���� 
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Connection conn = null;
	
	/**
	 * ��ȡ���ݿ�����
	 * @return ����һ�����Ӷ���
	 */
	public Connection getConnection(){
		try {
			//��������
			Class.forName(properties.getProperty("mydriver"));
			//��ȡ����
			conn = DriverManager.getConnection(properties.getProperty("myurl"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �ر���Դ
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public void close(Connection conn,PreparedStatement pstmt,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DataBaseConnection dbc = new DataBaseConnection();
		System.out.println(dbc.getConnection());
	}
}
