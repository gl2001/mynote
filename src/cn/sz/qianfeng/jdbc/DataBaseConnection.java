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

	private static final String FILENAME = "myconn.properties";//数据库连接参数存放在该文件中
	private static Properties properties = new Properties();//用来装载数据库连接参数
	private static InputStream is = null;
	static{
		//从src下读取properties文件的内容
		is = DataBaseConnection.class.getClassLoader().getResourceAsStream(FILENAME);
		try {
			//把上一步读取的内容存入properties对象中,方便后续建立连接时读取参数 
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Connection conn = null;
	
	/**
	 * 获取数据库连接
	 * @return 返回一个连接对象
	 */
	public Connection getConnection(){
		try {
			//加载驱动
			Class.forName(properties.getProperty("mydriver"));
			//获取连接
			conn = DriverManager.getConnection(properties.getProperty("myurl"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭资源
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
