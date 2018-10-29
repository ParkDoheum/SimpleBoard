package kr.co.hk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {
	public static Connection getConnction() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "hr";
		String password = "hkitedu";
		Connection con = DriverManager.getConnection(url,username,password);
		System.out.println("연결 성공!!");
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try { rs.close(); } catch(Exception e) {e.printStackTrace();}
		}
		
		if(ps != null) {
			try { ps.close(); } catch(Exception e) {e.printStackTrace();}
		}
		
		if(con != null) {
			try { con.close(); } catch(Exception e) {e.printStackTrace();}
		}
	}	
}
