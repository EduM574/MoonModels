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
import model.Validation;
import service.AlunoService;


/**
 * Servlet implementation class CadastroAlunoController
 */
@WebServlet("/CadastroAluno.do")
public class CadastroAlunoController extends HttpServlet {
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
		String pDataNascimento = request.getParameter("nascimento");
		String pCurso = request.getParameter("curso");
		String pSemestre = request.getParameter("semestre");
		String pUnidade = request.getParameter("unidade");
		String pTurno = request.getParameter("turno");
				
		String[] dataSeparada = pDataNascimento.split("-");
		int ano = Integer.parseInt(dataSeparada[0]);
		int mes = Integer.parseInt(dataSeparada[1]);
		int dia = Integer.parseInt(dataSeparada[2]);
		
		GregorianCalendar cal = new GregorianCalendar(ano, mes, dia);
		
		int sem = Integer.parseInt(pSemestre);
		
		HttpSession session = request.getSession();
		Administrador adm = (Administrador) session.getAttribute("adm");
		
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
		aln.setAdm(adm);
		
		AlunoService as = new AlunoService();
		Validation v = as.createValidation(aln);
		
		
		if(v.getStatus()) {
			request.setAttribute("erro", v.getText());
			RequestDispatcher view = request.getRequestDispatcher("cadastroAluno.jsp");
			view.forward(request, response);
		} else {
			as.create(aln);
			
			String data = "O RA do aluno cadastrado é " + aln.getRa() + " e sua senha inicial é seu CPF.";
			request.setAttribute("data", data);
        
			RequestDispatcher view = request.getRequestDispatcher("cadastroUserOkAdm.jsp");
			view.forward(request, response);
		}
		
	}

}
