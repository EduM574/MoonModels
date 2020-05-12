package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Solicitacao;
import model.Aluno;

import service.SolicitacaoService;

@WebServlet("/UserHomeAluno.do")
public class UserHomeAlunoController extends HttpServlet {
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
		Aluno aluno = (Aluno) session.getAttribute("aluno");
		
		SolicitacaoService slcService = new SolicitacaoService();
		ArrayList<Solicitacao> solicitacaoDeferida = slcService.selectSolicitacoesAluno(aluno, "DEFERIDA");
		ArrayList<Solicitacao> solicitacaoIndeferida = slcService.selectSolicitacoesAluno(aluno, "INDEFERIDA");
		ArrayList<Solicitacao> solicitacaoAndamento = slcService.selectSolicitacoesAluno(aluno, "");
		
		session.setAttribute("solicitacaoDeferida", solicitacaoDeferida);
		session.setAttribute("solicitacaoIndeferida", solicitacaoIndeferida);
		session.setAttribute("solicitacaoAndamento", solicitacaoAndamento);
		
		response.sendRedirect("userHomeAluno.jsp");
	
		}
	}