package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Setor;

public class SetorDAO {
	private Connection conexao;
	
	public SetorDAO() {
		this(null);
	}
	
	public SetorDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public ArrayList<Setor> setorOrder(){
    	String consulta = "SELECT * FROM setor ORDER BY codigo ASC;";
    	
    	try(PreparedStatement pst = conexao.prepareStatement(consulta)){
    		
    		ResultSet resultado = pst.executeQuery();
    		
    		ArrayList<Setor> setor = new ArrayList<Setor>();
    		
    		while(resultado.next()) {
    			Setor setor1 = new Setor(); 
    			
    			String nome = resultado.getString("nome");
    			int codigo = resultado.getInt("codigo");
    			
    			setor1.setNome(nome);
    			setor1.setIdSetor(codigo);
    			setor.add(setor1);
    		}
    		
    		return setor;
    		
    	} catch(SQLException e) {
    		System.err.println("Falha no banco: " + e.getMessage());
    		e.printStackTrace();
    	} catch( Exception e) {
    		System.err.println("Falha no java: " + e.getMessage());
    		e.printStackTrace();
    	}
    
    	return null;
    }
}
