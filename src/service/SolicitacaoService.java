package service;

import dao.Conexao;
import dao.SolicitacaoDAO;
import model.Solicitacao;

public class SolicitacaoService {
	SolicitacaoDAO soliDAO = new SolicitacaoDAO(Conexao.conectar());
	
	public void create(Solicitacao solicitacao) {
		solicitacao.setStatus("SOLICITADO");
		soliDAO.createSolicitacao(solicitacao);
	}

}
