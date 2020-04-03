package dao;

import java.sql.*;
import model.Solicitacao;
import java.io.*;

public class SolicitacaoDAO {
	private Connection conexao;
	
	public SolicitacaoDAO() {
		this(null);
	}
	
	public SolicitacaoDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void createSolicitacao(Solicitacao solicitacao) {
		String create = "INSERT INTO solicitacao(nome, descricao, anexo, statusS, data_abertura, prazo, fk_ra_aluno)" + "VALUES (?, ?, ?, ?, curdate(), ?, ?)";
		
		try(PreparedStatement pst = conexao.prepareStatement(create)){
			
			pst.setString(1, solicitacao.getNome());
			pst.setString(2, solicitacao.getDescricao());
			pst.setString(4, solicitacao.getStatus());
			pst.setInt(5, solicitacao.getPrazo());
			pst.setInt(6, solicitacao.getAluno().getRa());
			
			if(solicitacao.getAnexo() != null) {
				FileInputStream inputStream = new FileInputStream(solicitacao.getAnexo());
				pst.setBinaryStream(3, (InputStream) inputStream, (int)(solicitacao.getAnexo().length()));
			} else {
				pst.setBinaryStream(3, null);
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
	
	public void updateSolicitacao(Solicitacao solicitacao) {
		String update = "UPDATE solicitacao SET statusS = ?" + "WHERE codigo = ?";
		
		try(PreparedStatement pst = conexao.prepareStatement(update)){
			
			pst.setString(1, solicitacao.getStatus());
			pst.setInt(2, solicitacao.getIdSolicitacao());
			
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
