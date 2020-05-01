package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			request.setAttribute("erro", v.getText());
        
			RequestDispatcher view = request.getRequestDispatcher("loginAluno.jsp");
			view.forward(request, response);

		} else {
			aln = as.selectAluno(aln);
			HttpSession session = request.getSession();
			session.setAttribute("aluno", aln);

			response.sendRedirect("UserHome.do");

		}
	}

}
