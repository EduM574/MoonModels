package model;

import java.util.ArrayList;

public class Setor {
	private int identificadorSetor;
	private String nome;
	private String descricao;
	private String responsavel;
	private ArrayList<Administrador> administradores;
	
	public Setor() {}
	
	public Setor(int identificadorSetor, String nome, String descricao, String responsavel, ArrayList<Administrador> administradores) {
		this.identificadorSetor = identificadorSetor;
		this.nome = nome;
		this.descricao = descricao;
		this.responsavel = responsavel;
		this.administradores = administradores;
	}
	
	public int getIdSetor() {
		return identificadorSetor;
	}
	
	public void SetIdSetor(int idSetor) {
		this.identificadorSetor = idSetor;
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
	
	public String getResponsavel() {
		return responsavel;
	}
	
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	public ArrayList<Administrador> getAdministradores() {
		return administradores;
	}
	
	public void setAdministradores(ArrayList<Administrador> administradores) {
		this.administradores = administradores;
	}
	
}
