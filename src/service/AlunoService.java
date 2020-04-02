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
		alunoDAO.createAluno(aluno);
    }

//    public void updateInicial(Aluno aluno) {
//        adm.setStatus("ATIVO");
//        alunoDAO.updateInicialAdministrador(adm);
//    }
//    
//    public void updateTotal(Aluno aluno) {
//        alunoDAO.updateTotalAdministrador(adm);
//    }
//
//    public void updateSenha(Aluno aluno) {
//        alunoDAO.updateSenhaAdministrador(adm);
//    }
}