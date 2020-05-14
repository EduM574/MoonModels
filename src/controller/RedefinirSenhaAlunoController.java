package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aluno;
import service.AlunoService;


/**
 * Servlet implementation class RedefinirSenhaAlunoController
 */
@WebServlet("/redefinirSenhaAluno.do")
public class RedefinirSenhaAlunoController extends HttpServlet {
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
        int pRa = Integer.parseInt(request.getParameter("ra"));
        String pSenha = request.getParameter("password");

        Aluno al = new Aluno();
        al.setRa(pRa);
        al.setSenha(pSenha);

        AlunoService aService = new AlunoService();
        aService.updateSenha(al);

        response.sendRedirect("loginAluno.jsp");
    }
    
}