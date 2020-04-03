package service;

import dao.Conexao;
import dao.ComentarioDAO;
import model.Comentario;

public class ComentarioService {
	ComentarioDAO comDAO = new ComentarioDAO(Conexao.conectar());
	
	public void create(Comentario comentario) {
		comDAO.createComentario(comentario);
	}

}
