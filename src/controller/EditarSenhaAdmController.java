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
import service.AdministradorService;

/**
 * Servlet implementation class EditarSenhaAlunoController
 */
@WebServlet("/EditarSenhaAdm.do")
public class EditarSenhaAdmController extends HttpServlet {
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
		String pSenha = request.getParameter("password");
	
		//Passando senha nova para o objeto vindo da sessao
		adm.setSenha(pSenha);
		
		AdministradorService as = new AdministradorService();
		//Usando o objeto da sessao para update
		as.updateSenha(adm);
		
		//Atualizando os dados na sessao apos update
		session.setAttribute("adm", adm);

		String title = "Senha alterada com sucesso.";
		request.setAttribute("title", title);
	
		RequestDispatcher view = request.getRequestDispatcher("mensagemOkAdm.jsp");
		view.forward(request, response);
	}

}
