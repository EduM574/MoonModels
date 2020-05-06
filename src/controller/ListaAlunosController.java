package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aluno;
import service.AlunoService;

/**
 * Servlet implementation class ListaAlunosController
 */
@WebServlet("/ListaAlunos.do")
public class ListaAlunosController extends HttpServlet {
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
	
		AlunoService as = new AlunoService();
		ArrayList<Aluno> alunos = as.listarAlunos();
	
		request.setAttribute("alunos", alunos);
	
		RequestDispatcher view = request.getRequestDispatcher("listaAlunos.jsp");
		view.forward(request, response);
	}
}
