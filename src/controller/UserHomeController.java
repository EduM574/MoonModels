package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserHome.do")
public class UserHomeController extends HttpServlet {
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

		HttpSession session = request.getSession(false);
		
		if (session == null) {
			//caso a pessoa não esteja logada
			response.sendRedirect("loginAluno.jsp");
			System.out.println("Nenhum usuario logado");

		} else if (session.getAttribute("aluno") != null) {
			//caso a pessoa que esteja logada seja um aluno
			response.sendRedirect("userHomeAluno.jsp");
			
		} else if (session.getAttribute("adm") != null) {
			//caso a pessoa que esteja logada seja um adm
			response.sendRedirect("userHomeAdm.jsp");
		}
	}
}
