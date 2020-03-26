package model;

import java.util.Date;
import java.io.File;


public class Comentario {
	private int identificadorSolicitacao;
	private String nome;
	private String descricao;
	private File anexo;
	private String status;
	private Date data_abertura;
	private int prazo;
	private Aluno aluno;
	
	public Comentario() {}
	
	public Comentario(int identificadorSolicitacao, String nome, String descricao, File anexo, String status, Date data_abertura, int prazo, Aluno aluno) {
		
		this.identificadorSolicitacao = identificadorSolicitacao;
		this.nome = nome;
		this.descricao = descricao;
		this.anexo = anexo;
		this.status = status;
		this.data_abertura = data_abertura;
		this.prazo = prazo;
		this.aluno = aluno;
	}
	
	public int getIdSolicitacao() {
		return identificadorSolicitacao;
	}
	public void SetIdSolicitacao(int idSolicitacao) {
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
	
	public Date getDataAbertura() {
		return data_abertura;
	}
	public void setDataAbertura(Date dataAbertura) {
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
}
