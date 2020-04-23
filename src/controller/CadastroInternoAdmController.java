package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class CadastroInternoAdmController
 */
@WebServlet("/CadastroInternoAdm.do")
public class CadastroInternoAdmController extends HttpServlet {
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
		
		String pEmail = request.getParameter("email");
		String pSetor = request.getParameter("setor");
		
		int setConv = Integer.parseInt(pSetor);
		
		Setor setor = new Setor();
		setor.setIdSetor(setConv);
		
		Administrador adm = new Administrador();
		adm.setEmail(pEmail);
		adm.setSetor(setor);
		
		AdministradorService as = new AdministradorService();
		Validation v = as.createValidation(adm);
		
		if(v.getStatus()) {
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Cadastro Interno Administrador</title></head><body>");
			out.println(	"Erro = " + v.getText() + "<br>");
			out.println("</body></html>");
		} else {
		as.create(adm);
		adm = as.selectAdminGeral(adm);
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Cadastro Interno Administrador</title></head><body>");
		out.println(	"Email = " + adm.getEmail() + "<br>");
		out.println(	"Setor = " + adm.getSetor() + "<br>");
		out.println("</body></html>");
		}
	}

}
