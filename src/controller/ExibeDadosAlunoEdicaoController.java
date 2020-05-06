package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aluno;
import service.AlunoService;

/**
 * Servlet implementation class ExibeDadosAlunoEdicaoController
 */
@WebServlet("/ExibeDadosAlunoEdicao.do")
public class ExibeDadosAlunoEdicaoController extends HttpServlet {
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

        int ra = Integer.parseInt(request.getParameter("ra-aluno"));

        Aluno aln = new Aluno();
        aln.setRa(ra);

        AlunoService aService = new AlunoService();
        aln = aService.selectAluno(aln);
 
        request.setAttribute("exibeAluno", aln);

        RequestDispatcher view = request.getRequestDispatcher("editarDadosAluno.jsp");
        view.forward(request, response);

    }

}