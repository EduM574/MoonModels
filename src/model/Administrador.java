package model;

import java.util.ArrayList;

public class Administrador {
	
	private String nome;
	private String sobrenome;
	private String status;
	private int cpf;
	private String email;
	private String senha;
	private String setor;
	private ArrayList<Aluno> alunos;
	private ArrayList<Solicitacao> solicitacoes;

	public Administrador() {
	}

	public Administrador(String nome, String sobrenome, String status, int cpf, String email, String senha,
			String setor, ArrayList<Aluno> alunos, ArrayList<Solicitacao> solicitacoes) {

		this.nome = nome;
		this.sobrenome = sobrenome;
		this.status = status;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.setor = setor;
		this.alunos = alunos;
		this.solicitacoes = solicitacoes;
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

	public int getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getSetor() {
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

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void setSolicitacoes(ArrayList<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

}
