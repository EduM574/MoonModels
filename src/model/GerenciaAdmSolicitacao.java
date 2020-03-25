package model;

import java.util.Date;

public class GerenciaAdmSolicitacao {
	private int identificadorGerencia;
	private Administrador administrador;
	private Solicitacao solicitacao;
	private Date data;

	public GerenciaAdmSolicitacao() {
	}

	public GerenciaAdmSolicitacao(int identificadorGerencia, Administrador administrador, Solicitacao solicitacao,
			Date data) {

		this.identificadorGerencia = identificadorGerencia;
		this.administrador = administrador;
		this.solicitacao = solicitacao;
		this.data = data;
	}

	public int getIdGerenciamento() {
		return identificadorGerencia;
	}

	public void setIdGerenciamento(int idGerenciamento) {
		this.identificadorGerencia = idGerenciamento;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
