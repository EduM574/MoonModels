package service;

import java.util.ArrayList;

import dao.Conexao;
import dao.SolicitacaoDAO;
import model.Solicitacao;
import model.Aluno;

public class SolicitacaoService {
	SolicitacaoDAO soliDAO = new SolicitacaoDAO(Conexao.conectar());
	
	public void create(Solicitacao solicitacao) {
		solicitacao.setStatus("SOLICITADO");
		soliDAO.createSolicitacao(solicitacao);
	}
	
	public void update(Solicitacao solicitacao) {
		soliDAO.updateSolicitacao(solicitacao);
	}

	public ArrayList<Solicitacao> selectSolicitacoesAluno(Aluno aluno, String statusSolicitacao) {
		if(aluno.getStatus().equals("ATIVO")){
			return soliDAO.solicitacoesAluno(aluno, statusSolicitacao);
		} else {
			return null;
		}
	}
}
