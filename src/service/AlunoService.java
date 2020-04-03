package service;

import dao.Conexao;
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
}