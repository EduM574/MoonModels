package model;

import java.util.ArrayList;

public class Administrador {
	
	private String nome;
	private String sobrenome;
	private String status;
	private String cpf;
	private String email;
	private String senha;
	private Setor setor;
	private ArrayList<Aluno> alunos;
	private ArrayList<Solicitacao> solicitacoes;
	private ArrayList<Comentario> comentarios;
	
	public Administrador() {
	}

	public Administrador(String nome, String sobrenome, String status, String cpf, String email, String senha,
			Setor setor, ArrayList<Aluno> alunos, ArrayList<Solicitacao> solicitacoes,
			ArrayList<Comentario> comentarios) {
	
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.status = status;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.setor = setor;
		this.alunos = alunos;
		this.solicitacoes = solicitacoes;
		this.comentarios = comentarios;
	}

	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ArrayList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getStatus() {
		return status;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Setor getSetor() {
		return setor;
	}

	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}

	public ArrayList<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void setSolicitacoes(ArrayList<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

}
