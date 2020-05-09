package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Administrador;
import model.Comentario;
import model.Solicitacao;
import service.ComentarioService;
import service.SolicitacaoService;

/**
 * Servlet implementation class UserHomeSolicitacaoController
 */
@WebServlet("/UserHomeSolicitacao.do")
public class UserHomeSolicitacaoController extends HttpServlet {
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
        
        int idSolicitacao = Integer.parseInt(request.getParameter("id-solicitacao"));

        //Pegando os dados da solicitacao
        SolicitacaoService sService = new SolicitacaoService();
        Solicitacao solicitacao = sService.selectSolicitacao(idSolicitacao);
        System.out.println("Solicitacao antes dos comentarios:"+solicitacao);

        //Pegando os comentarios da solicitacao
        ComentarioService cService = new ComentarioService();
        ArrayList<Comentario> comentarios = cService.selectComentariosDados(solicitacao);
        System.out.println("Comentarios"+comentarios);

        //Adicionando os comentarios na solicitacao
        solicitacao.setComentario(comentarios);
        System.out.println("Solicitacao depois dos comentarios:"+solicitacao);

        // response.sendRedirect("userHomeSolicitacaoAdm.jsp");
    }
}