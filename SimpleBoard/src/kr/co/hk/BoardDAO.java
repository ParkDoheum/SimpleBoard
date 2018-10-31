package kr.co.hk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	//맥스 board_no 가져오기
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
	
	//등록
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
	
	//보드 리스트 가져오기
	public static List<BoardVO> getBoardList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT A.BOARD_NO, A.BOARD_TITLE "
				+ " , TO_CHAR(A.REGDATE, 'YYYY.MM.DD') AS REGDATE "
				+ " , A.CNT, COUNT(B.COMMENT_NO) AS COMMENT_CNT "
				+ " FROM S_BOARD A "
				+ " LEFT JOIN S_COMMENT B"
				+ " ON A.BOARD_NO = B.BOARD_NO"
				+ " GROUP BY A.BOARD_NO, A.BOARD_TITLE, A.REGDATE, A.CNT "
				+ " ORDER BY BOARD_NO DESC ";
		try {
			con = DBConn.getConnction();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int board_no = rs.getInt("board_no");
				String board_title = rs.getString("board_title");
				String regdate = rs.getString("regdate");
				int cnt = rs.getInt("cnt");
				int comment_cnt = rs.getInt("comment_cnt");
				
				System.out.println("board_no : " + board_no);
				System.out.println("board_title : " + board_title);
				System.out.println("regdate : " + regdate);
				System.out.println("cnt : " + cnt);
				System.out.println("comment_cnt : " + comment_cnt);
				System.out.println();
				System.out.println();
				
				BoardVO vo = new BoardVO();
				vo.setBoard_no(board_no);
				vo.setBoard_title(board_title);
				vo.setRegdate(regdate);
				vo.setCnt(cnt);
				vo.setComment_cnt(comment_cnt);
				
				list.add(vo);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}		
		return list;
	}
	
	//보드 디테일
	public static BoardVO getBoardDetail(int board_no) {
		BoardVO vo = new BoardVO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT BOARD_TITLE, BOARD_CONTENT"
				+ " , TO_CHAR(REGDATE, 'YYYY.MM.DD') AS REGDATE"
				+ " , CNT"
				+ " FROM S_BOARD"
				+ " WHERE board_no = ? ";
		
		try {
			con = DBConn.getConnction();
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				
				String board_title = rs.getString("board_title");
				String board_content = rs.getString("board_content");
				String regdate = rs.getString("regdate");
				int cnt = rs.getInt("cnt");
				
				System.out.println("board_no : " + board_no);
				System.out.println("board_title : " + board_title);
				System.out.println("board_content : " + board_content);
				System.out.println("regdate : " + regdate);
				System.out.println("cnt : " + cnt);
				System.out.println();
				System.out.println();
				
				vo.setBoard_no(board_no);
				vo.setBoard_title(board_title);
				vo.setBoard_content(board_content);
				vo.setRegdate(regdate);
				vo.setCnt(cnt);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		return vo;
	}
	
	//조회수 +1
	public static int boardUpdateCnt(int board_no) {
		int result = -1;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " UPDATE S_BOARD"
				+ " SET cnt = cnt + 1"
				+ " WHERE board_no = ? ";
		try {			
			con = DBConn.getConnction();
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			result = ps.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
		}		
		return result;
	}
	
	//글삭제
	public static void  deleteBoard(int board_no) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " DELETE FROM S_BOARD WHERE board_no = ? ";
		try {			
			con = DBConn.getConnction();
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			ps.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
		}
	}
	
	public static int updateBoard(BoardVO vo) {
		int result = -1;
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " UPDATE S_BOARD "
				+ " SET board_title = ? "
				+ " , board_content = ? "
				+ " , regdate = ? "
				+ " WHERE board_no = ? ";
		try {			
			con = DBConn.getConnction();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getBoard_title());
			ps.setString(2,  vo.getBoard_content());
			ps.setString(3,  vo.getRegdate());
			ps.setInt(4, vo.getBoard_no());
			
			result = ps.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
		}
		
		return result;
	}
	
	//댓글 달기
	public static int insertComment(CommentVO vo) {
		int result = -1;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " INSERT INTO S_COMMENT "
				+ " (board_no, comment_no, comment_content, regdate)"
				+ " values"
				+ " (?, (select nvl(max(comment_no), 0) + 1 from S_COMMENT"
				+ "      WHERE board_no = ?) "
				+ "  , ?, sysdate) ";
		
		try {
			con = DBConn.getConnction();
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getBoard_no());
			ps.setInt(2, vo.getBoard_no());
			ps.setString(3, vo.getComment_content());
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
		}
		
		return result;
	}
	
	//댓글 가져오기
	public static List<CommentVO> getCommentList(int board_no) {
		List<CommentVO> list = new ArrayList<CommentVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT COMMENT_NO, COMMENT_CONTENT "
				+ " , TO_CHAR(REGDATE, 'YYYY.MM.DD') AS REGDATE "
				+ " FROM S_COMMENT "
				+ " WHERE BOARD_NO = ?"
				+ " ORDER BY COMMENT_NO DESC ";
		try {
			con = DBConn.getConnction();
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery();
			while(rs.next()) {
				int comment_no = rs.getInt("comment_no");
				String comment_content = rs.getString("comment_content");
				String regdate = rs.getString("regdate");
				
				System.out.println("comment_no : " + comment_no);
				System.out.println("comment_content : " + comment_content);
				System.out.println("regdate : " + regdate);
				
				CommentVO vo = new CommentVO();
				vo.setComment_no(comment_no);
				vo.setComment_content(comment_content);
				vo.setRegdate(regdate);
				
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		return list;
	}
	
	//댓글 삭제 (1개 삭제 ,게시글 댓글 삭제)
	public static void deleteComment(int board_no, int comment_no) {		
		Connection con = null;
		PreparedStatement ps = null;		
		String sql = " DELETE FROM S_COMMENT"
				+ " WHERE board_no = ? ";
		
		if(comment_no > 0) {
			sql += " and comment_no = " + comment_no;
		}
		
		try {
			con = DBConn.getConnction();
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);			
			ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
		}
	}
	

	
}












