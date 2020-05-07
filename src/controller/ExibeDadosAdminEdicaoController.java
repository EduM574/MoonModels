package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Administrador;
import service.AdministradorService;

@WebServlet("/ExibeDadosAdminEdicao.do")
public class ExibeDadosAdminEdicaoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("email-admin");
    	
    	Administrador adm = new Administrador();
    	adm.setEmail(email);
    	
    	AdministradorService admService = new AdministradorService();
    	adm = admService.selectAdminGeral(adm);
    	
    	request.setAttribute("exibeAdmin", adm);
    	
    	RequestDispatcher view = request.getRequestDispatcher("editarDadosAdm.jsp");
        view.forward(request, response);    	
    }

}
