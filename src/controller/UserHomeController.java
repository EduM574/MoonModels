package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Administrador;
import model.Aluno;

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
		PrintWriter out = response.getWriter();

		if (session == null) {
			//caso a pessoa não esteja logada
			response.sendRedirect("loginAluno.jsp");
			System.out.println("Nenhum usuario logado");

		} else if (session.getAttribute("aluno") != null) {
			//caso a pessoa que esteja logada seja um aluno
			Aluno aluno = (Aluno) session.getAttribute("aluno");

			System.out.println("Aluno logado");
			out.println("<html><head><title>Login Aluno</title></head><body>");
			out.println(	"Aluno = " + aluno);
			out.println("</body></html>");

		} else if (session.getAttribute("adm") != null) {
			//caso a pessoa que esteja logada seja um adm
			Administrador adm = (Administrador) session.getAttribute("adm");
			
			System.out.println("Adm logado");
			out.println("<html><head><title>Login Aluno</title></head><body>");
			out.println(	"Aluno = " + adm);
			out.println("</body></html>");
		}
	}
}
