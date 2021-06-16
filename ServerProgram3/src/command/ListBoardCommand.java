package command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.Board;

public class ListBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		int totalRecord = BoardDAO.getInstance().getTotal();
		
		
		List<Board> list = BoardDAO.getInstance().boardList();
		
		
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("list", list);
		
		ModelAndView mav = new ModelAndView("/board/listBoard.jsp", false);
		return mav;
	}

}
