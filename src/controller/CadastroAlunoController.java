package controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		Administrador adm = new Administrador();
		adm.setEmail("gerald.cortes@email.com");
		
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
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Cadastro Aluno</title></head><body>");
			out.println(	"Erro = " + v.getText() + "<br>");
			out.println("</body></html>");
		} else {
			as.create(aln);
			aln = as.selectAluno(aln);
			
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Cadastro Aluno</title></head><body>");
			out.println(	"Nome = " + aln.getNome() + "<br>");
			out.println(	"Sobrenome = " + aln.getSobrenome() + "<br>");
			out.println(	"CPF = " + aln.getCpf() + "<br>");
			out.println(	"Email = " + aln.getEmail() + "<br>");
			out.println(	"Data de Nascimento = " + aln.getData_nascimento() + "<br>");
			out.println(	"Curso = " + aln.getCurso() + "<br>");
			out.println(	"Turno = " + aln.getTurno() + "<br>");
			out.println(	"Unidade = " + aln.getUnidade() + "<br>");
			out.println(	"Semestre = " + aln.getSemestre() + "<br>");
			out.println("</body></html>");		
		}
		
	}

}
