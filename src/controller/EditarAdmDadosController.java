package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Administrador;
import model.Setor;
import model.Validation;
import service.AdministradorService;

/**
 * Servlet implementation class EditarAlunoDadosController
 */
@WebServlet("/EditarAdmDados.do")
public class EditarAdmDadosController extends HttpServlet {
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

		String pNome = request.getParameter("nome");
		String pCpf = request.getParameter("cpf");
		int pSetor = Integer.parseInt(request.getParameter("setor"));
		String pSobrenome = request.getParameter("sobrenome");
		String pSenha = request.getParameter("password");
		String pStatus = request.getParameter("status");
		String pEmail = request.getParameter("email-admin");

		pCpf = pCpf.replace("." , "");
		pCpf = pCpf.replace("-" , "");
		
		Setor st = new Setor();
		st.setIdSetor(pSetor);

		Administrador adm = new Administrador();
		adm.setNome(pNome);
		adm.setSobrenome(pSobrenome);
		adm.setCpf(pCpf);
		adm.setSetor(st);
		adm.setStatus(pStatus);
		adm.setEmail(pEmail);

		AdministradorService as = new AdministradorService();
		
		if (pSenha.equals("")) {
			Administrador alTemp = as.selectAdminGeral(adm);
			adm.setSenha(alTemp.getSenha());
		} else {
			adm.setSenha(pSenha);
		}
		
		Validation v = as.updateValidation(adm);
		
		if (v.getStatus()) {
			request.setAttribute("erro", v.getText());
			RequestDispatcher view = request.getRequestDispatcher("ExibeDadosAdminEdicao.do");
			view.forward(request, response);

		} else {
			as.updateTotal(adm);

			String title = "Dados do administrador alterados com sucesso.";
			String data = "";
			request.setAttribute("title", title);
			request.setAttribute("data", data);

			RequestDispatcher view = request.getRequestDispatcher("mensagemOkAdm.jsp");
			view.forward(request, response);
		}

	}

}
