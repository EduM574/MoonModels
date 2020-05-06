package controller;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import model.Aluno;
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
		
		String[] dataSeparada = pDataNascimento.split("-");
		int ano = Integer.parseInt(dataSeparada[0]);
		int mes = Integer.parseInt(dataSeparada[1]);
		int dia = Integer.parseInt(dataSeparada[2]);
		
		int sem = Integer.parseInt(pSemestre);
		
		GregorianCalendar cal = new GregorianCalendar(ano, mes, dia);
		
		Aluno aln = new Aluno();
		aln.setNome(pNome);
		aln.setSobrenome(pSobrenome);
		aln.setCpf(pCpf);
		aln.setEmail(pEmail);
		aln.setSenha(pSenha);
		aln.setData_nascimento(cal);
		aln.setCurso(pCurso);
		aln.setSemestre(sem);
		aln.setUnidade(pUnidade);
		aln.setTurno(pTurno);
		aln.setStatus(pStatus);
		//RA e ADM para testes
		aln.setRa(819100000);
		
		AlunoService as = new AlunoService();
		as.updateDoAdm(aln);
		
		PrintWriter out = response.getWriter();
		out.println("RA: " + aln.getRa());
		out.println("Nome: " + aln.getNome());
		out.println("Sobrenome: " + aln.getSobrenome());
		out.println("CPF: " + aln.getCpf());
		out.println("E-Mail: " + aln.getEmail());
		out.println("Senha: " + aln.getSenha());
		out.println("Data de Nascimento: " + aln.getData_nascimento());
		out.println("Curso: " + aln.getCurso());
		out.println("Semestre: " + aln.getSemestre());
		out.println("Unidade: " + aln.getUnidade());
		out.println("Turno: " + aln.getTurno());
		out.println("Status: " + aln.getStatus());
		
		
	// UM ADIANTO PRA VCS
		
//		HttpSession session = request.getSession();
//		session.setAttribute("adm", adm);
//
//		response.sendRedirect("listaAlunos.jsp");
	}

}
