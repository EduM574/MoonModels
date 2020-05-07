package controller;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aluno;
import model.Validation;
import service.AlunoService;

/**
 * Servlet implementation class EditarAlunoDadosController
 */
@WebServlet("/EditarAlunoDados.do")
public class EditarAlunoDadosController extends HttpServlet {
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
		String pNome = request.getParameter("nome");
		String pSobrenome = request.getParameter("sobrenome");
		String pCpf = request.getParameter("cpf");
		String pEmail = request.getParameter("email");
		String pSenha = request.getParameter("password");
		String pDataNascimento = request.getParameter("nascimento");
		String pCurso = request.getParameter("curso");
		String pSemestre = request.getParameter("semestre");
		String pUnidade = request.getParameter("unidade");
		String pTurno = request.getParameter("turno");
		String pStatus = request.getParameter("status");
		String pRa = request.getParameter("ra-aluno");
		
		String[] dataSeparada = pDataNascimento.split("-");
		int ano = Integer.parseInt(dataSeparada[0]);
		int mes = Integer.parseInt(dataSeparada[1]);
		int dia = Integer.parseInt(dataSeparada[2]);
		
		int sem = Integer.parseInt(pSemestre);
		int ra = Integer.parseInt(pRa);
		
		GregorianCalendar cal = new GregorianCalendar(ano, mes, dia);
		
		AlunoService as = new AlunoService();
		Aluno aln = new Aluno();
		aln.setNome(pNome);
		aln.setSobrenome(pSobrenome);
		aln.setCpf(pCpf);
		aln.setEmail(pEmail);
		aln.setData_nascimento(cal);
		aln.setCurso(pCurso);
		aln.setSemestre(sem);
		aln.setUnidade(pUnidade);
		aln.setTurno(pTurno);
		aln.setStatus(pStatus);
		aln.setRa(ra);

		if(pSenha.equals("")) {
			Aluno alTemp = as.selectAluno(aln);
			aln.setSenha(alTemp.getSenha());
		} else {
			aln.setSenha(pSenha);
		}

		Validation v = as.updateValidation(aln);

		if(v.getStatus()) {
			request.setAttribute("erro", v.getText());
			RequestDispatcher view = request.getRequestDispatcher("ExibeDadosAlunoEdicao.do");
			view.forward(request, response);
		} else {
			as.updateDoAdm(aln);
			
			String title = "Dados do aluno alterados com sucesso.";
			String data = "";
			request.setAttribute("title", title);
			request.setAttribute("data", data);
		
			RequestDispatcher view = request.getRequestDispatcher("mensagemOkAdm.jsp");
			view.forward(request, response);
		}
		

	}

}
