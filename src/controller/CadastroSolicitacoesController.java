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
import model.Solicitacao;
import service.SolicitacaoService;

/**
 * Servlet implementation class CadastroAdmController
 */
@WebServlet("/CadastroSolicitacoes.do")
public class CadastroSolicitacoesController extends HttpServlet {
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

		String sNome = request.getParameter("solicitacao");
		String sDescricao = request.getParameter("descricao");

		HttpSession session = request.getSession();
		Aluno al = (Aluno) session.getAttribute("aluno");
		
		Solicitacao sol = new Solicitacao();
		sol.setNome(sNome);
		sol.setDescricao(sDescricao);
		sol.setAluno(al);
		// sol.setAnexo(file);

		SolicitacaoService ss = new SolicitacaoService();
		ss.create(sol);

		String title = "Solicitação realizada com sucesso.";
		request.setAttribute("title", title);
	
		RequestDispatcher view = request.getRequestDispatcher("mensagemOkAluno.jsp");
		view.forward(request, response);

	}
}