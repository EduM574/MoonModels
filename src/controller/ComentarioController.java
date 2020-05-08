package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// atributos capturados
		String cTexto = request.getParameter("texto");
		// COLOCAR CAPTURA DO ANEXO e fks

		// criar obj
		Aluno al = new Aluno();
		al.setRa(3);

		Administrador adm = new Administrador();
		adm.setEmail("fulano2@usjt.br");

		Solicitacao sol = new Solicitacao();
		sol.setIdSolicitacao(5);

		Comentario cm = new Comentario();
		cm.setTexto(cTexto);
		cm.setAluno(al);
		cm.setSolicitacao(sol);
		cm.setAdministrador(adm);

		ComentarioService cs = new ComentarioService();
		cs.create(cm);

		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Cadastro Administrador</title></head><body>");
		out.println("Texto = " + cm.getTexto() + "<br>");
		out.println("Ra Aluno = " + cm.getAluno().getRa() + "<br>");
		out.println("Email Administrador= " + cm.getAdministrador().getEmail() + "<br>");
		out.println("Id solicição " + cm.getSolicitacao().getIdSolicitacao() + "<br>");
		out.println("</body></html>");
	}

}
