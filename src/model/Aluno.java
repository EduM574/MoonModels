package model;

import java.util.ArrayList;
import java.util.Date;

public class Aluno {
	private int identificadorAluno;
	private String nome;
	private String sobrenome;
	private int ra;
	private String status;
	private int cpf;
	private String data_nascimento;// converter para date depois?
	private String curso;
	private String turno;
	private String unidade;
	private String semestre;
	private String email;
	private String senha;
	private Administrador adm;
	private ArrayList<Solicitacao> solicitacoes;

	public Aluno() {
	}

	public Aluno(int identificadorAluno, String nome, String sobrenome, int ra, String status, int cpf,
			String data_nascimento, String curso, String turno, String unidade, String semestre, String email,
			String senha, Administrador adm, ArrayList<Solicitacao> solicitacoes) {

		this.identificadorAluno = identificadorAluno;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.ra = ra;
		this.status = status;
		this.cpf = cpf;
		this.data_nascimento = data_nascimento;
		this.curso = curso;
		this.turno = turno;
		this.unidade = unidade;
		this.semestre = semestre;
		this.email = email;
		this.senha = senha;
		this.adm = adm;
		this.solicitacoes = solicitacoes;
	}

	// SEM RA
	public Aluno(int identificadorAluno, String nome, String sobrenome, String status, int cpf, String data_nascimento,
			String curso, String turno, String unidade, String semestre, String email, String senha, Administrador adm,
			ArrayList<Solicitacao> solicitacoes) {

		this.identificadorAluno = identificadorAluno;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.status = status;
		this.cpf = cpf;
		this.data_nascimento = data_nascimento;
		this.curso = curso;
		this.turno = turno;
		this.unidade = unidade;
		this.semestre = semestre;
		this.email = email;
		this.senha = senha;
		this.adm = adm;
		this.solicitacoes = solicitacoes;
	}

	public Aluno(String nome, String sobrenome, String status, int cpf, String data_nascimento, String curso,
			String turno, String unidade, String semestre, String email, String senha, Administrador adm,
			ArrayList<Solicitacao> solicitacoes) {

		this.nome = nome;
		this.sobrenome = sobrenome;
		this.status = status;
		this.cpf = cpf;
		this.data_nascimento = data_nascimento;
		this.curso = curso;
		this.turno = turno;
		this.unidade = unidade;
		this.semestre = semestre;
		this.email = email;
		this.senha = senha;
		this.adm = adm;
		this.solicitacoes = solicitacoes;
	}

	public int getIdentificadorAluno() {
		return identificadorAluno;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public int getRa() {
		return ra;
	}

	public String getStatus() {
		return status;
	}

	public int getCpf() {
		return cpf;
	}

	public String getData_nascimento() {
		return data_nascimento;
	}

	public String getCurso() {
		return curso;
	}

	public String getTurno() {
		return turno;
	}

	public String getUnidade() {
		return unidade;
	}

	public String getSemestre() {
		return semestre;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Administrador getAdm() {
		return adm;
	}

	public ArrayList<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setIdentificadorAluno(int identificadorAluno) {
		this.identificadorAluno = identificadorAluno;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setAdm(Administrador adm) {
		this.adm = adm;
	}

	public void setSolicitacoes(ArrayList<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	
}
