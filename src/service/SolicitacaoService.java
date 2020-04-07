package service;

import java.util.ArrayList;

import dao.Conexao;
import dao.SolicitacaoDAO;
import model.Solicitacao;
import model.Administrador;
import model.Aluno;

public class SolicitacaoService {
	SolicitacaoDAO soliDAO = new SolicitacaoDAO(Conexao.conectar());
	
	public void create(Solicitacao solicitacao) {
		solicitacao.setStatus("ABERTA");
		soliDAO.createSolicitacao(solicitacao);
	}
	
	public void update(Solicitacao solicitacao) {
		soliDAO.updateSolicitacao(solicitacao);
	}

	public ArrayList<Solicitacao> selectSolicitacoesAlunoDefInd(Aluno aluno, String statusSolicitacao) {
		if(aluno.getStatus().equals("ATIVO")) {
			if(statusSolicitacao.equals("INDEFERIDA") || statusSolicitacao.equals("DEFERIDA")) {
				return soliDAO.solicitacoesDeferidaIndeferidaAluno(aluno, statusSolicitacao);
			} else {
				return soliDAO.solicitacoesAtivaAluno(aluno);
			}
		} else {
			return null;
		}
	}

	// public ArrayList<Solicitacao> selectSolicitacoesADM(Administrador adm) {
	// 	if(aluno.getStatus().equals("ATIVO")) {
	// 		return soliDAO.solicitacoesDeferidaIndeferidaAluno(aluno, statusSolicitacao);
	// 	} else {
	// 		return null;
	// 	}
	// }

	
}
