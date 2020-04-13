package service;

import dao.Conexao;

import java.util.ArrayList;

import dao.AlunoDAO;
import model.Aluno;

public class AlunoService {
	AlunoDAO alunoDAO = new AlunoDAO(Conexao.conectar());

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
}