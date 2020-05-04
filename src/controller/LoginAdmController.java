package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Administrador;
import model.Validation;
import service.AdministradorService;

/**
 * Servlet implementation class LoginAdmController
 */
@WebServlet("/LoginAdmin.do")
public class LoginAdmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pEmail = request.getParameter("email");
		String pSenha = request.getParameter("password");
		
		Administrador adm = new Administrador();
		adm.setEmail(pEmail);
		adm.setSenha(pSenha);
		
		AdministradorService as = new AdministradorService();
		Validation v = as.loginValidation(adm);
		
		if(v.getStatus()) {
			request.setAttribute("erro", v.getText());
        
			RequestDispatcher view = request.getRequestDispatcher("loginAdm.jsp");
			view.forward(request, response);
		} else {
			adm = as.selectAdminGeral(adm);
			HttpSession session = request.getSession();
			session.setAttribute("adm", adm);

			response.sendRedirect("UserHomeAdm.jsp");
		}
	}

}
