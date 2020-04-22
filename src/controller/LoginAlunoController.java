package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aluno;
import service.AlunoService;
import model.Validation;

/**
 * Servlet implementation class LoginAlunoController
 */
@WebServlet("/LoginAluno.do")
public class LoginAlunoController extends HttpServlet {
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

		String pRa = request.getParameter("ra");
		String pSenha = request.getParameter("password");
		
		int raConv = Integer.parseInt(pRa);
		
		Aluno aln = new Aluno();
		aln.setRa(raConv);
		aln.setSenha(pSenha);
		
		AlunoService as = new AlunoService();
		Validation v = as.loginValidation(aln);
		
		if(v.getStatus()) {
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Login Aluno</title></head><body>");
			out.println(	"Erro = " + v.getText() + "<br>");
			out.println("</body></html>");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Login Aluno</title></head><body>");
			out.println(	"Email = " + aln.getRa() + "<br>");
			out.println(	"Senha = " + aln.getSenha() + "<br>");
			out.println("</body></html>");
		}
	}

}
