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

    public void updateTotalAdministrador(Administrador adm) {
        String update = "UPDATE administrador SET nome = ?, sobrenome = ?, cpf = ?, "
                        + "statusA = ?, senha = ?, fk_cod_setor = ? "
                        + "WHERE email = ?";

        try(PreparedStatement pst = conexao.prepareStatement(update)) {

            pst.setString(1, adm.getNome());
            pst.setString(2, adm.getSobrenome());
            pst.setString(3, adm.getCpf());
            pst.setString(4, adm.getStatus());
            pst.setString(5, adm.getSenha());
            pst.setInt(6, adm.getSetor().getIdSetor());
            pst.setString(7, adm.getEmail());

            pst.execute();

        } catch(SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch ( Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
    }

    public void updateInicialAdministrador(Administrador adm) {
        String update = "UPDATE administrador SET nome = ?, sobrenome = ?, cpf = ?, "
                        + "statusA = ?, senha = ? "
                        + "WHERE email = ?";

        try(PreparedStatement pst = conexao.prepareStatement(update)) {

            pst.setString(1, adm.getNome());
            pst.setString(2, adm.getSobrenome());
            pst.setString(3, adm.getCpf());
            pst.setString(4, adm.getStatus());
            pst.setString(5, adm.getSenha());
            pst.setString(6, adm.getEmail());

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