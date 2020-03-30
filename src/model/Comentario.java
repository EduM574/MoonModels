package model;

import java.util.GregorianCalendar;
import java.io.File;

public class Comentario {
	private int identificadorComentario;
	private String texto;
	private GregorianCalendar data_hora;
	private File anexo;
	private Aluno aluno;
	private Administrador administrador;
	private Solicitacao solicitacao;
	
	public Comentario() {}
	
	public Comentario(int identificadorComentario, String texto, GregorianCalendar data_hora, File anexo, Aluno aluno, Administrador administrador, Solicitacao solicitacao) {
		
		this.identificadorComentario = identificadorComentario;
		this.texto = texto;
		this.data_hora = data_hora;
		this.anexo = anexo;
		this.aluno = aluno;
		this.administrador = administrador;
		this.solicitacao = solicitacao;
	}
	
	public int getIdComentario() {
		return identificadorComentario;
	}
	public void setIdComentario(int idComentario) {
		this.identificadorComentario = idComentario;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public GregorianCalendar getDataHora() {
		return data_hora;
	}
	public void setDataHora(GregorianCalendar dataHora) {
		this.data_hora = dataHora;
	}
	
	public File getAnexo() {
		return anexo;
	}
	public void setAnexo(File anexo) {
		this.anexo = anexo;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
}
