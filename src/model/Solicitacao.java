package model;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class Solicitacao {
	private int identificadorSolicitacao;
	private String nome;
	private String descricao;
	private File anexo;
	private String status;
	private GregorianCalendar data_abertura;
	private int prazo;
	private Aluno aluno;
	private ArrayList<Comentario> comentarios;
	
	public Solicitacao() {}
	
	public Solicitacao(int identificadorSolicitacao, String nome, String descricao, File anexo, String status, GregorianCalendar data_abertura, int prazo, Aluno aluno, ArrayList<Comentario> comentarios) {
		
		this.identificadorSolicitacao = identificadorSolicitacao;
		this.nome = nome;
		this.descricao = descricao;
		this.anexo = anexo;
		this.status = status;
		this.data_abertura = data_abertura;
		this.prazo = prazo;
		this.aluno = aluno;
		this.comentarios = comentarios;

	}
	
	public int getIdSolicitacao() {
		return identificadorSolicitacao;
	}
	public void setIdSolicitacao(int idSolicitacao) {
		this.identificadorSolicitacao = idSolicitacao;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public File getAnexo() {
		return anexo;
	}
	public void setAnexo(File anexo) {
		this.anexo = anexo;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public GregorianCalendar getDataAbertura() {
		return data_abertura;
	}
	public void setDataAbertura(GregorianCalendar dataAbertura) {
		this.data_abertura = dataAbertura;
	}
	
	public int getPrazo() {
		return prazo;
	}
	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public ArrayList<Comentario> getComentarios(){
		return comentarios;
	}
	public void setComentario(ArrayList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
}
