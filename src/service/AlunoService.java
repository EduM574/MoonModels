package service;

import java.util.ArrayList;
import model.Aluno;
import model.Validation;
import dao.ConnectionFactory;
import dao.AlunoDAO;

public class AlunoService {
	AlunoDAO alunoDAO = new AlunoDAO(ConnectionFactory.conectar());

	public void create(Aluno aluno) {
		aluno.setStatus("ATIVO");
		aluno.setSenha(aluno.getCpf());
		alunoDAO.createAluno(aluno);
	}

	public void updateSenha(Aluno aluno) {
		alunoDAO.updateSenhaAluno(aluno);
	}

	public void updateDoAdm(Aluno aluno) {
		alunoDAO.updateDoAdmAluno(aluno);
	}
	
	public Aluno selectAluno(Aluno aluno) {
		return alunoDAO.alunoBusca(aluno);
	}
	
	public ArrayList<Aluno> listarAlunos() {
		return alunoDAO.alunoOrder();
	}

	public Validation createValidation(Aluno aluno) {
		return alunoDAO.createValidation(aluno);
	}

	public Validation updateValidation(Aluno aluno) {
		return alunoDAO.updateValidation(aluno);
	}

	public Validation loginValidation(Aluno aluno) {
		return alunoDAO.loginValidation(aluno);
	}
}