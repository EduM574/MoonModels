package service;

import java.util.ArrayList;

import model.Setor;
import dao.Conexao;
import dao.SetorDAO;

public class SetorService {
	SetorDAO setorDAO = new SetorDAO (Conexao.conectar());

	public ArrayList<Setor> listarSetores() {
		return setorDAO.setorOrder();
	}
}