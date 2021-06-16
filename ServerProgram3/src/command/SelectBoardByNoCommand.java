package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.Board;

public class SelectBoardByNoCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		long no = Long.parseLong(request.getParameter("no"));
		
		Board board = BoardDAO.getInstance().selectBoard(no);
		
		BoardDAO.getInstance().updateHit(no);
		
		request.setAttribute("board", board);
		
		ModelAndView mav = new ModelAndView("/board/viewBoard.jsp", false);
		
		return mav;
		
		
		
	}

}
