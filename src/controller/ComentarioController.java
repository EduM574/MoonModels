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
import model.Aluno;
import model.Comentario;
import model.Solicitacao;
import service.ComentarioService;

/**
 * Servlet implementation class ComentarioController
 */
@WebServlet("/Comentario.do")
public class ComentarioController extends HttpServlet {
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
		String cTexto = request.getParameter("texto");
		int idSoli = Integer.parseInt(request.getParameter("id-solicitacao"));

		HttpSession session = request.getSession();
		Comentario cm = new Comentario();
		Solicitacao sol = new Solicitacao();

		sol.setIdSolicitacao(idSoli);
		cm.setTexto(cTexto);
		cm.setSolicitacao(sol);

		if(session.getAttribute("aluno") != null) {
			Aluno al = (Aluno) session.getAttribute("aluno");
			cm.setAluno(al);

        } else if(session.getAttribute("adm") != null) {
			Administrador adm = (Administrador) session.getAttribute("adm");
			cm.setAdministrador(adm);
        }

		ComentarioService cs = new ComentarioService();
		cs.create(cm);

		RequestDispatcher view = request.getRequestDispatcher("UserHomeSolicitacao.do");
		view.forward(request, response);
		
	}

}
