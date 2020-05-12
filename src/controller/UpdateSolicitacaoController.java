package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Solicitacao;
import service.SolicitacaoService;

/**
 * Servlet implementation class UpdateSolicitacaoController
 */
@WebServlet("/UpdateSolicitacao.do")
public class UpdateSolicitacaoController extends HttpServlet {
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

        int id = Integer.parseInt(request.getParameter("id-solicitacao"));
        String status = request.getParameter("status-soli");

        Solicitacao soli = new Solicitacao();
        soli.setIdSolicitacao(id);
        soli.setStatus(status);

        SolicitacaoService sService = new SolicitacaoService();
        sService.update(soli);

        RequestDispatcher view = request.getRequestDispatcher("UserHomeAdm.do");
		view.forward(request, response);
    }
}