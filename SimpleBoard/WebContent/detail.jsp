<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kr.co.hk.*" %>
<%
	List<CommentVO> commentList = (List<CommentVO>)request.getAttribute("commentList");
%>    
<h3>${vo.board_title} 조회수(${vo.cnt})</h3>
<div>날짜 : ${vo.regdate }</div>
<div>
	${vo.board_content}
</div>
<form action="detail" method="post">
	<input type="hidden" name="board_no" value="${vo.board_no}">
	<input type="hidden" name="type" value="del">
	<input type="submit" value="삭제">
</form>
<a href="mod?board_no=${vo.board_no}"><input type="button" value="수정"></a>

<br><br>
<div>
	<form action="comment" method="post">
		<input type="hidden" name="board_no" value="${vo.board_no}">
		댓글 <textarea name="comment_content"></textarea>
		<input type="submit" value="등록">
	</form>
</div>
<div>
<%
	if(commentList != null && commentList.size() > 0) {
%>
	<table>
		<tr>
			<th>번호</th>
			<th>내용</th>
			<th>날짜</th>
			<th>삭제</th>
		</tr>
		<% for(CommentVO vo : commentList) { %>
			<tr>
				<td><%=vo.getComment_no() %></td>
				<td><%=vo.getComment_content() %></td>
				<td><%=vo.getRegdate() %></td>
				<td>
					<a href="comment?board_no=${vo.board_no}&comment_no=<%=vo.getComment_no()%>">
						<button>삭제</button>
					</a>
				</td>
			</tr>		
		<% } %>
				
		
	</table>
<%
	}
%>
</div>