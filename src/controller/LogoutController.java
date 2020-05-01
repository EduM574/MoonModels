package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Logout.do")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        HttpSession session = request.getSession();
        String page;

        if (session.getAttribute("aluno") != null) {
			page = "loginAluno.jsp";

		} else if (session.getAttribute("adm") != null) {
            page = "loginAdm.jsp";

		} else {
            //caso não ache um específico
            page = "loginAluno.jsp";
        }
        
        session.invalidate();
        response.sendRedirect(page);
    }
    
}