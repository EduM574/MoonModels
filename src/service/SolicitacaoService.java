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

	public ArrayList<Solicitacao> selectSolicitacoesAluno(Aluno aluno, String statusSolicitacao) {
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

	public ArrayList<Solicitacao> selectSolicitacoesADM(Administrador adm) {

		if(adm.getSetor().getNome().equals("Transporte escolar")) {
			return soliDAO.solicitacoesADM("Bilhete da SPTrans");

		} else if(adm.getSetor().getNome().equals("Gestão de estagio")) {
			return soliDAO.solicitacoesADM("Contrato de estágio");

		} else if(adm.getSetor().getNome().equals("Atividades curriculares")) {
			ArrayList<Solicitacao> array1 = soliDAO.solicitacoesADM("Entrega de atividades complementares");
			ArrayList<Solicitacao> array2 = soliDAO.solicitacoesADM("Mudança de horário");
			
			ArrayList<Solicitacao> resultado = new ArrayList<Solicitacao>(array1.size()+ array2.size());
			resultado.addAll(array1);
			resultado.addAll(array2);

			return resultado;			 
		}

		return null;
	}
	
}
