package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Administrador;
import service.AdministradorService;

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
		String pCpf = request.getParameter("cpf");
		String pEmail = request.getParameter("email");
		String pSenha =  request.getParameter("password");
		
		Administrador adm = new Administrador();
		adm.setNome(pNome);
		adm.setSobrenome(pSobrenome);
		adm.setCpf(pCpf);
		adm.setEmail(pEmail);
		adm.setSenha(pSenha);
		
		AdministradorService as = new AdministradorService();
		as.updateInicial(adm);
//		adm = as.selectAdminGeral(adm);
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Cadastro Administrador</title></head><body>");
		out.println(	"Nome = " + adm.getNome() + "<br>");
		out.println(	"Sobrenome = " + adm.getSobrenome() + "<br>");		
		out.println(	"Cpf = " + adm.getCpf() + "<br>");
		out.println(	"Email = " + adm.getEmail() + "<br>");
		out.println(	"Senha = " + adm.getSenha() + "<br>");
		out.println("</body></html>");
	}

}
