package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.BoardDAO;
import dto.Board;

public class InsertBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String ip = request.getRemoteAddr();
		
		ModelAndView mav = null;
		
		try {
		Board board = new Board();
		board.setAuthor(author);
		board.setContent(content);
		board.setTitle(title);
		board.setIp(ip);
		
		int result = BoardDAO.getInstance().insertBoard(board);
		if(result == 0) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글이 저장되지 않앗습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글이 등록되었습니다..')");
			out.println("history.back()");
			out.println("</script>");
			mav = new ModelAndView("/ServerProgram3/listBoard.do", true);
		}
		
		
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return mav;
	}

}
