package service;

import dao.Conexao;
import dao.AdministradorDAO;
import dao.AlunoDAO;
import model.Administrador;
import model.Aluno;

public class AlunoService {
	AlunoDAO alunoDAO = new AlunoDAO(Conexao.conectar());

	public void create(Aluno aluno) {
		aluno.setStatus("ATIVO");
		aluno.setSenha(aluno.getCpf());
		alunoDAO.createAluno(aluno);
	}

	public void updateSenha(Aluno aluno) {
		aluno.setStatus("ATIVO");
		alunoDAO.updateSenhaAluno(aluno);
	}

	public void updateDoAdm(Aluno aluno) {
		aluno.setStatus("ATIVO");
		alunoDAO.updateDoAdmAluno(aluno);
	}
}