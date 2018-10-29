package kr.co.hk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardDAO {
	public static int getMaxBoardNo() {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT NVL(MAX(BOARD_NO), 100000) + 1 "
				+ " FROM S_BOARD ";
		
		try {
			con = DBConn.getConnction();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		return result;
	}
	
	public static int insertBoard(BoardVO vo) {
		int result = -1;
				
		Connection con = null;
		PreparedStatement ps = null;		
		
		String sql = " INSERT INTO s_board"
				+ " (board_no, board_title, board_content, regdate, cnt)"
				+ " values"
				+ " (?, ?, ?, ?, 0) ";
		
		try {
			con = DBConn.getConnction();
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getBoard_no());
			ps.setString(2,  vo.getBoard_title());
			ps.setString(3,  vo.getBoard_content());
			ps.setString(4,  vo.getRegdate());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
		}		
		return result;
	}
}





