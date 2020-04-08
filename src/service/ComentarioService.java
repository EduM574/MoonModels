package service;

import dao.Conexao;
import dao.ComentarioDAO;
import model.Comentario;
import model.Solicitacao;
import java.util.ArrayList;

public class ComentarioService {
	ComentarioDAO comDAO = new ComentarioDAO(Conexao.conectar());
	
	public void create(Comentario comentario) {
		comDAO.createComentario(comentario);
	}
	
	public void update(Comentario comentario) {
		comDAO.updateComentario(comentario);
	}
	
	public ArrayList<Comentario> selectComentariosDados(Solicitacao solicitacao) {
		return comDAO.comentariosDados(solicitacao);
	}

}
