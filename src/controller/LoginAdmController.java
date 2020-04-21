package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Login Administrador</title></head><body>");
			out.println(	"Erro = " + v.getText() + "<br>");
			out.println("</body></html>");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Login Administrador</title></head><body>");
			out.println(	"Email = " + adm.getEmail() + "<br>");
			out.println(	"Senha = " + adm.getSenha() + "<br>");
			out.println("</body></html>");
		}
	}

}
