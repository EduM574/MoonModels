package dao;

import java.sql.*;
import model.Administrador;
import java.util.ArrayList;
import model.Setor;

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

    public void updateSenhaAdministrador(Administrador adm) {
        String update = "UPDATE administrador SET senha = ? WHERE email = ?";

        try(PreparedStatement pst = conexao.prepareStatement(update)) {

            pst.setString(1, adm.getSenha());
            pst.setString(2, adm.getEmail());

            pst.execute();

        } catch(SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch ( Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
    }
    
    public Administrador adminGeral(Administrador adm){
    	String consulta = "SELECT * FROM administrador WHERE email = ?;";
    	
    	try(PreparedStatement pst = conexao.prepareStatement(consulta)) {
    		
    		pst.setString(1, adm.getEmail());
    		
    		ResultSet resultado = pst.executeQuery();
    		
			Administrador admini = new Administrador();
			Setor set = new Setor();

    		if(resultado.next()) {
    			
    			String nome = resultado.getString("nome");
    			String sobrenome = resultado.getString("sobrenome");
    			String cpf = resultado.getString("cpf");
    			String statusA = resultado.getString("statusA");
    			String email = resultado.getString("email");
    			String senha = resultado.getString("senha");
    			int fkCodSetor = resultado.getInt("fk_cod_setor");
    			
    			set.setIdSetor(fkCodSetor);
    			
    			admini.setNome(nome);
    			admini.setSobrenome(sobrenome);
    			admini.setCpf(cpf);
    			admini.setStatus(statusA);
    			admini.setEmail(email);
    			admini.setSenha(senha);
    			admini.setSetor(set);
    			
    		}
    		
    		return admini;
    		
    	} catch(SQLException e) {
    		System.err.println("Falha no banco: " + e.getMessage());
    		e.printStackTrace();
    	} catch( Exception e) {
    		System.err.println("Falha no java: " + e.getMessage());
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public ArrayList<Administrador> adminOrder(){
    	String consulta = "SELECT * FROM administrador ORDER BY nome ASC;";
    	
    	try(PreparedStatement pst = conexao.prepareStatement(consulta)){
    		
    		ResultSet resultado = pst.executeQuery();
    		
    		ArrayList<Administrador> admin2 = new ArrayList<Administrador>();
    		
    		while(resultado.next()) {
    			Administrador admini2 = new Administrador();
    			Setor set2 = new Setor();
    			
    			String nome = resultado.getString("nome");
    			String sobrenome = resultado.getString("sobrenome");
    			String cpf = resultado.getString("cpf");
    			String statusA = resultado.getString("statusA");
    			String email = resultado.getString("email");
    			String senha = resultado.getString("senha");
    			int fkCodSetor = resultado.getInt("fk_cod_setor");
    			
    			set2.setIdSetor(fkCodSetor);
    			
    			admini2.setNome(nome);
    			admini2.setSobrenome(sobrenome);
    			admini2.setCpf(cpf);
    			admini2.setStatus(statusA);
    			admini2.setEmail(email);
    			admini2.setSenha(senha);
    			admini2.setSetor(set2);
    			
    			admin2.add(admini2);
    		}
    		
    		return admin2;
    		
    	} catch(SQLException e) {
    		System.err.println("Falha no banco: " + e.getMessage());
    		e.printStackTrace();
    	} catch( Exception e) {
    		System.err.println("Falha no java: " + e.getMessage());
    		e.printStackTrace();
    	}
    
    	return null;
	}
	
	public boolean createValidation(Administrador adm) {
		String consulta = "SELECT * FROM administrador WHERE email = ?;";

		try(PreparedStatement pst = conexao.prepareStatement(consulta)){
			
			pst.setString(1, adm.getEmail());
			ResultSet resultado = pst.executeQuery();

			if(resultado.next()) {
				return true;
			} else {
				return false;
			}
			
		} catch(SQLException e) {
    		System.err.println("Falha no banco: " + e.getMessage());
    		e.printStackTrace();
    	} catch( Exception e) {
    		System.err.println("Falha no java: " + e.getMessage());
    		e.printStackTrace();
    	}
		
		return false;
	}
}