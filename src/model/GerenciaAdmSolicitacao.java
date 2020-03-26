package model;

import java.util.Date;
import java.util.GregorianCalendar;

public class GerenciaAdmSolicitacao {
	
	private Administrador administrador;
	private Solicitacao solicitacao;
	private GregorianCalendar data;

	public GerenciaAdmSolicitacao() {
	}

	public GerenciaAdmSolicitacao(int identificadorGerencia, Administrador administrador, Solicitacao solicitacao,
			GregorianCalendar data) {

		this.administrador = administrador;
		this.solicitacao = solicitacao;
		this.data = data;
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

}
