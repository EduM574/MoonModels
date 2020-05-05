package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Administrador;
import model.Setor;
import service.AdministradorService;
import model.Validation;

/**
 * Servlet implementation class CadastroAdmController
 */
@WebServlet("/CadastraAdm.do")
public class CadastroAdmController extends HttpServlet {
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
		
		String pNome = request.getParameter("nome");
		String pSobrenome = request.getParameter("sobrenome");
		String pEmail = request.getParameter("email");
		String pCpf = request.getParameter("cpf");
		String pSetor = request.getParameter("setor");

		int setConv = Integer.parseInt(pSetor);
		Setor setor = new Setor();
		setor.setIdSetor(setConv);
		
		Administrador adm = new Administrador();
		adm.setNome(pNome);
		adm.setSobrenome(pSobrenome);
		adm.setEmail(pEmail);
		adm.setCpf(pCpf);
		adm.setSetor(setor);
		
		AdministradorService as = new AdministradorService();
		Validation v = as.createValidation(adm);
		
		if(v.getStatus()) {
			request.setAttribute("erro", v.getText());
			RequestDispatcher view = request.getRequestDispatcher("cadastroAdm.jsp");
			view.forward(request, response);
		} else {
			as.create(adm);
			response.sendRedirect("cadastroUserOkAdm.jsp");
		}
	}

}
