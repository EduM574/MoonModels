package dao;

import java.sql.*;
import model.Comentario;
import java.io.*;

public class ComentarioDAO {
	private Connection conexao;
	
	public ComentarioDAO() {
		this(null);
	}
	
	public ComentarioDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public int createComentario(Comentario comentario) {
		String create = "INSERT INTO comentario(texto, data_hora, anexo, fk_ra_aluno, fk_email_adm, fk_codigo_solicitacao)" + "VALUES (?, now(), ?, ?, ?, ?)";
		
		try(PreparedStatement pst = conexao.prepareStatement(create)){
			
			pst.setString(1, comentario.getTexto());
			pst.setInt(3, comentario.getAluno().getRa());
			pst.setString(4, comentario.getAdministrador().getEmail());
			pst.setInt(5, comentario.getSolicitacao().getIdSolicitacao());
			
			if(comentario.getAnexo() != null) {
				FileInputStream inputStream = new FileInputStream(comentario.getAnexo());
				pst.setBinaryStream(2, (InputStream) inputStream, (int)(comentario.getAnexo().length()));
			} else {
				pst.setBinaryStream(2, null);
			}
			
			pst.execute();

			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conexao.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					comentario.setIdComentario(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch(SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch( Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}

		return comentario.getIdComentario();
	}
	
	public void updateComentario(Comentario comentario) {
		String update = "UPDATE comentario SET texto = ?, anexo = ? " + "WHERE codigo = ?";
		
		try(PreparedStatement pst = conexao.prepareStatement(update)) {
			
			pst.setString(1, comentario.getTexto());
			pst.setInt(3, comentario.getIdComentario());
			
			if(comentario.getAnexo() != null) {
				FileInputStream inputStream = new FileInputStream(comentario.getAnexo());
				pst.setBinaryStream(2, (InputStream) inputStream, (int)(comentario.getAnexo().length()));
			} else {
				pst.setBinaryStream(2, null);
			}
			
			pst.execute();
			
		} catch(SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch( Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
	} 
}