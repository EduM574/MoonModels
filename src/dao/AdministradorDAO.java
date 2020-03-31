package dao;

import java.sql.*;

import model.Administrador;

public class AdministradorDAO {
    private Connection conexao;
	
	public AdministradorDAO() {
		this(null);
	}
	
	public AdministradorDAO(Connection conexao) {
		this.conexao = conexao;
    }
    
    public void createAdminitrador(Administrador adm) {
        String create = "INSERT INTO administrador(email, statusA, fk_cod_setor)" 
                        + "VALUES (?, ?, ?)";

        try(PreparedStatement pst = conexao.prepareStatement(create)) {

            pst.setString(1, adm.getEmail());
            pst.setString(2, adm.getStatus());
            pst.setInt(3, adm.getSetor().getIdSetor());

            pst.execute();

        } catch(SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch ( Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
    }
}