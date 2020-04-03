package dao;

import java.sql.*;
import model.GerenciaAdmSolicitacao;

public class GerenciaAdmSolicitacaoDAO {
    private Connection conexao;
	
	public GerenciaAdmSolicitacaoDAO() {
		this(null);
	}
	
	public GerenciaAdmSolicitacaoDAO(Connection conexao) {
		this.conexao = conexao;
    }
    
    public void createRegistro(GerenciaAdmSolicitacao gerencia) {
        String create = "INSERT INTO gerencia_adm_solicitacao(data_hora, statusS, descricao, fk_email_adm, fk_codigo_solicitacao) "
        + "VALUES (now(), ?, ?, ?, ?)";

        try(PreparedStatement pst = conexao.prepareStatement(create)){

            pst.setString(1, gerencia.getSolicitacao().getStatus());
            pst.setString(2, gerencia.getDescricao());
            pst.setString(3, gerencia.getAdministrador().getEmail());
			pst.setInt(4, gerencia.getSolicitacao().getIdSolicitacao());
			
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