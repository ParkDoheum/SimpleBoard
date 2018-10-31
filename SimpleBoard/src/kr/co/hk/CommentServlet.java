package kr.co.hk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--------- comment [GET] -------");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--------- comment [POST] -------");
		request.setCharacterEncoding("UTF-8");
		String comment_content = request.getParameter("comment_content");
		String board_no = request.getParameter("board_no");		
		System.out.println("comment_content : " + comment_content);
		System.out.println("board_no : " + board_no);
		
		int intBoardNo = Integer.parseInt(board_no);
		CommentVO vo = new CommentVO();
		vo.setBoard_no(intBoardNo);
		vo.setComment_content(comment_content);
		
		int result = BoardDAO.insertComment(vo);
		
		response.sendRedirect("detail?board_no=" + board_no);
	}
	
	

}







