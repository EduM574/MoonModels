package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Administrador;
import model.Solicitacao;
import service.SolicitacaoService;

/**
 * Servlet implementation class UserHomeAdmController
 */
@WebServlet("/UserHomeAdm.do")
public class UserHomeAdmController extends HttpServlet {
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
 
		HttpSession session = request.getSession();
		Administrador adm = (Administrador) session.getAttribute("adm");

		SolicitacaoService sService = new SolicitacaoService();
		ArrayList<Solicitacao> solicitacoes = sService.selectSolicitacoesADM(adm);

		session.setAttribute("solicitacoesAdm", solicitacoes);
		response.sendRedirect("userHomeAdm.jsp");
    }
}