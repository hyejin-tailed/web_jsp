package net.board.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.*;


public class Delete implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{

		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		boolean result=false;
		boolean usercheck=false;
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO boarddao = new BoardDAO();
		usercheck = boarddao.isBoardWriter(num, request.getParameter("B_PASS"));
		

		if(usercheck==false){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할권한이 없습니다.')");
			out.println("location.href='/BoardList.do'");
			out.println("</script>");

		}
		result=boarddao.boardDelete(num);
		if(result==false){
			System.out.println("게시판 삭제 실패");
			return null;
		}
		
		
		System.out.println("게시판 삭제 성공");
		forward.setRedirect(true);
		forward.setPath("/BoardList.do");
		
		
		return forward;
	}
}
