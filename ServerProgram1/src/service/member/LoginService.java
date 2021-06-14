package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class LoginService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setName(name);
		
		MemberDTO loginDTO = MemberDAO.getInstance().login(dto);
		
		
		ModelAndView mav = new ModelAndView("/10_MODEL2/index.do", true);//true는 redirect를 의미
		return mav;
	}

}
