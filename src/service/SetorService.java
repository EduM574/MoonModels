package service;

import dao.Conexao;
import dao.SetorDAO;

import java.util.ArrayList;

import dao.AlunoDAO;
import model.Aluno;
import model.Setor;

public class SetorService {
	SetorDAO setorDAO = new SetorDAO (Conexao.conectar());

	public ArrayList<Setor> listarSetores() {
		return setorDAO.setorOrder();
	}
}