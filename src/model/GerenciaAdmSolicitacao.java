package model;

import java.util.Date;
import java.util.GregorianCalendar;

public class GerenciaAdmSolicitacao {
	
	private Administrador administrador;
	private Solicitacao solicitacao;
	private GregorianCalendar data;
	private String status;
	private String descricao;

	public GerenciaAdmSolicitacao() {
	}

	public GerenciaAdmSolicitacao(int identificadorGerencia, Administrador administrador, Solicitacao solicitacao,
			GregorianCalendar data, String status, String descricao) {

		this.administrador = administrador;
		this.solicitacao = solicitacao;
		this.data = data;
		this.status = status;
		this.descricao = descricao;
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

	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar data) {
		this.data = data;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
