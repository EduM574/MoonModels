package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Administrador;
import model.Aluno;
import model.Solicitacao;
import service.AdministradorService;
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
		// atributos capturados
		String sNome = request.getParameter("solicitacao");
		String sDescricao = request.getParameter("descricao");
		// COLOCAR CAPTURA DO ANEXO
		// COLETAR FK ALUNO E ADM

		// criar obj
		Aluno al = new Aluno();
		al.setRa(4);// id pronto
		Solicitacao sol = new Solicitacao();
		sol.setNome(sNome);
		sol.setDescricao(sDescricao);
		sol.setAluno(al);

		SolicitacaoService ss = new SolicitacaoService();
		ss.create(sol);

		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Cadastro Administrador</title></head><body>");
		out.println("Nome da Solicitação = " + sol.getNome() + "<br>");
		out.println("Descrição = " + sol.getDescricao() + "<br>");
		out.println("Ra = " + sol.getAluno().getRa() + "<br>");
		out.println("</body></html>");
	}

}
