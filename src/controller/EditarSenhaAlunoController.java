package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Aluno;
import service.AlunoService;

/**
 * Servlet implementation class EditarSenhaAlunoController
 */
@WebServlet("/EditarSenhaAluno.do")
public class EditarSenhaAlunoController extends HttpServlet {
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
		String pSenha = request.getParameter("password");
		
		Aluno aln = new Aluno();
		aln.setSenha(pSenha);
		aln.setRa(819100001);
		
		AlunoService as = new AlunoService();
		as.updateSenha(aln);
		
		PrintWriter out = response.getWriter();
		out.println("Nova senha: " + aln.getSenha());
		
		//Ye
//		HttpSession session = request.getSession();
//		session.setAttribute("aluno", aln);
//
//		response.sendRedirect("userHomeAluno.jsp");
	}

}
