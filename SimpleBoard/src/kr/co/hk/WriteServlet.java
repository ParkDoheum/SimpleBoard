package kr.co.hk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/write")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" ----WriteServlet [GET] ----- ");
				
		int board_no = BoardDAO.getMaxBoardNo();
		System.out.println("board_no : " + board_no);
		request.setAttribute("board_no", board_no);
		Utils.dispatcher("글쓰기", "write", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" ----WriteServlet [POST] ----- ");		
		request.setCharacterEncoding("UTF-8");
		String board_no = request.getParameter("board_no");
		String board_title = request.getParameter("board_title");
		String board_content = request.getParameter("board_content");
		String regdate = request.getParameter("regdate");
		
		System.out.println("board_no : " + board_no);
		System.out.println("board_title : " + board_title);
		System.out.println("board_content : " + board_content);
		System.out.println("regdate : " + regdate);
		
		int intBoardNO = Integer.parseInt(board_no);
		
		BoardVO vo = new BoardVO();
		vo.setBoard_no(intBoardNO);
		vo.setBoard_title(board_title);
		vo.setBoard_content(board_content);
		vo.setRegdate(regdate);
		
		int result = BoardDAO.insertBoard(vo);
		System.out.println("result : " + result);
		
		response.sendRedirect("list");
	}

}
