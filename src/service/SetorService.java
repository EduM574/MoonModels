package service;

import java.util.ArrayList;

import model.Setor;
import dao.ConnectionFactory;
import dao.SetorDAO;

public class SetorService {
	SetorDAO setorDAO = new SetorDAO (ConnectionFactory.conectar());

	public ArrayList<Setor> listarSetores() {
		return setorDAO.setorOrder();
	}
}