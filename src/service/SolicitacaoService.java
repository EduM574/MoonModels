package service;

import java.util.ArrayList;

import dao.ConnectionFactory;
import dao.SolicitacaoDAO;
import model.Solicitacao;
import model.Administrador;
import model.Aluno;

public class SolicitacaoService {
	SolicitacaoDAO soliDAO = new SolicitacaoDAO(ConnectionFactory.conectar());

	public void create(Solicitacao solicitacao) {
		solicitacao.setStatus("Aberta");

		if (solicitacao.getNome().equals("Contrato de estagio")) {
			solicitacao.setPrazo(15);

		} else if (solicitacao.getNome().equals("Bilhete da SPTrans")) {
			solicitacao.setPrazo(20);

		} else if (solicitacao.getNome().equals("Entrega de atividades complementares")) {
			solicitacao.setPrazo(8);

		} else if (solicitacao.getNome().equals("Mudanca de horario")) {
			solicitacao.setPrazo(10);
		}

		soliDAO.createSolicitacao(solicitacao);
	}

	public void update(Solicitacao solicitacao) {
		soliDAO.updateSolicitacao(solicitacao);
	}

	public ArrayList<Solicitacao> selectSolicitacoesAluno(Aluno aluno, String statusSolicitacao) {
		if (aluno.getStatus().equals("ATIVO")) {
			if (statusSolicitacao.equals("INDEFERIDA") || statusSolicitacao.equals("DEFERIDA")) {
				return soliDAO.solicitacoesDeferidaIndeferidaAluno(aluno, statusSolicitacao);
			} else {
				return soliDAO.solicitacoesAtivaAluno(aluno);
			}
		} else {
			return null;
		}
	}

	public ArrayList<Solicitacao> selectSolicitacoesADM(Administrador adm) {

		if (adm.getSetor().getIdSetor() == 1) {
			return soliDAO.solicitacoesADM("Bilhete da SPTrans");

		} else if (adm.getSetor().getIdSetor() == 2) {
			return soliDAO.solicitacoesADM("Contrato de estágio");

		} else if (adm.getSetor().getIdSetor() == 3) {
			ArrayList<Solicitacao> array1 = soliDAO.solicitacoesADM("Entrega de atividades complementares");
			ArrayList<Solicitacao> array2 = soliDAO.solicitacoesADM("Mudança de horário");

			ArrayList<Solicitacao> resultado = new ArrayList<Solicitacao>(array1.size() + array2.size());
			resultado.addAll(array1);
			resultado.addAll(array2);

			return resultado;
		} else if (adm.getSetor().getIdSetor() == 4) {
			return soliDAO.solicitacoesADMaster();
		}

		return null;
	}

	public Solicitacao selectSolicitacao(int id) {
		return soliDAO.selectSolicitacao(id);
	}
}
