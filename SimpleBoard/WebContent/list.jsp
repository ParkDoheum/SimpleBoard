<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kr.co.hk.*" %>
<%
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
%>

<script>
	function clkTr(board_no) {		
		//location.href="detail?board_no=" + board_no;
		var form = document.createElement("form");
	    form.method = "POST";
	    form.action = "detail";   

	    var element1 = document.createElement("input");	    
	    element1.value=board_no;
	    element1.name="board_no";
	    
	    form.appendChild(element1);
	    document.body.appendChild(form);
	    form.submit();
	}
</script>
<table>
	<tr>
		<th>번호</th>
		<th>타이틀</th>
		<th>등록날짜</th>
		<th>조회수</th>
		<th>댓글수</th>
	</tr>
<%
	if(list != null && list.size() > 0) {
		for(BoardVO vo : list) {
%>	
	<tr onclick="clkTr(<%=vo.getBoard_no() %>)" style="cursor:pointer">
		<td><%=vo.getBoard_no() %></td>
		<td><%=vo.getBoard_title() %></td>		
		<td><%=vo.getRegdate() %></td>
		<td><%=vo.getCnt() %></td>
		<td><%=vo.getComment_cnt() %></td>
	</tr>
<%		
		}
	}
%>


</table>