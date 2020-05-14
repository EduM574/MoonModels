package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Administrador;
import service.AdministradorService;



/**
 * Servlet implementation class RedefinirSenhaAdmController
 */
@WebServlet("/RedefinirSenhaAdm.do")
public class RedefinirSenhaAdmController extends HttpServlet {
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
        String pSenha = request.getParameter("password");

        Administrador adm = new Administrador();
        adm.setEmail(pEmail);
        adm.setSenha(pSenha);

        AdministradorService admS = new AdministradorService();
        admS.updateSenha(adm);

        response.sendRedirect("loginAdm.jsp");
    }
    
}