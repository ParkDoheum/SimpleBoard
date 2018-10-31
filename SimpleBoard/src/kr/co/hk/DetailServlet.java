package kr.co.hk;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------------ detail [GET] -------");
		String board_no = request.getParameter("board_no");
		System.out.println("board_no : " + board_no);
		
		int intBarodNO = Integer.parseInt(board_no);
		
		BoardVO vo = BoardDAO.getBoardDetail(intBarodNO);
		request.setAttribute("vo", vo);
		
		List<CommentVO> commentList = BoardDAO.getCommentList(intBarodNO);
		
		request.setAttribute("commentList", commentList);		
		Utils.dispatcher("디테일", "detail", request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//조회수 올리기
		System.out.println("------------ detail [POST] -------");
		String board_no = request.getParameter("board_no");
		System.out.println("board_no : " + board_no);
		int intBoardNo = Integer.parseInt(board_no);
		BoardDAO.boardUpdateCnt(intBoardNo);		
		response.sendRedirect("detail?board_no=" + board_no);
	}
}





