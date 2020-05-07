package controller;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Administrador;
import model.Aluno;
import model.Setor;
import service.AdministradorService;
import service.AlunoService;

/**
 * Servlet implementation class EditarAlunoDadosController
 */
@WebServlet("/EditarAdmDados.do")
public class EditarAdmDadosController extends HttpServlet {
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
		HttpSession session = request.getSession();
		 Administrador ad =(Administrador) session.getAttribute("adm");
		
		String pNome = request.getParameter("nome");
		String pCpf = request.getParameter("cpf");
		String pSetor = request.getParameter("setor");
		String pSobrenome = request.getParameter("sobrenome");
		String pSenha = request.getParameter("password");
		String pStatus = request.getParameter("status");

		Setor st = new Setor();
		if(pSetor.equals("GDE")) {
			st.setIdSetor(2);
		}else if(pSetor.equals("TPE")) {
			st.setIdSetor(1);
		}else {
			st.setIdSetor(3);
		}
		Administrador adm = new Administrador();
		adm.setNome(pNome);
		adm.setSobrenome(pSobrenome);
		adm.setCpf(pCpf);
		adm.setSenha(pSenha);
		adm.setSetor(st);
		adm.setStatus(pStatus);
		adm.setEmail(ad.getEmail());
		
		AdministradorService as = new AdministradorService();
		as.updateTotal(adm);
		
		String title = "Dados do administrador alterados com sucesso.";
		String data = "";
		request.setAttribute("title", title);
		request.setAttribute("data", data);
	
		RequestDispatcher view = request.getRequestDispatcher("mensagemOkAdm.jsp");
		view.forward(request, response);

	}

}
