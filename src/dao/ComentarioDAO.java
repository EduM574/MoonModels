package dao;

import java.sql.*;
import model.Comentario;
import java.io.*;
import java.util.ArrayList;
import model.Solicitacao;
import java.util.GregorianCalendar;
import model.Aluno;
import model.Administrador;

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
	
	public ArrayList<Comentario> comentariosDados(Solicitacao solicitacao) {
		String consulta = "SELECT * FROM comentario WHERE fk_codigo_solicitacao = ? ORDER BY data_hora ASC;";
		
		try(PreparedStatement pst = conexao.prepareStatement(consulta)){
			
			pst.setInt(1, solicitacao.getIdSolicitacao());
			
			ResultSet resultado = pst.executeQuery();
			
			ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
			
			while(resultado.next()) {
				Comentario comenta = new Comentario();
				Aluno alu = new Aluno();
				Administrador admin = new Administrador();
				Solicitacao solici = new Solicitacao();
				
				int codigo =  resultado.getInt("codigo");
				String texto = resultado.getString("texto");
				int fkAluno = resultado.getInt("fk_ra_aluno");
				String fkAdm = resultado.getString("fk_email_adm");
				int fkSolicitacao = resultado.getInt("fk_codigo_solicitacao");
				InputStream anexo = resultado.getBinaryStream("anexo");
				
				String dataBanco = resultado.getString("data_abertura");
				String[] dataSeparada = dataBanco.split("-");
				int ano = Integer.parseInt(dataSeparada[0]);
				int mes = Integer.parseInt(dataSeparada[1]);
				int dia = Integer.parseInt(dataSeparada[2]);				
				GregorianCalendar data = new GregorianCalendar(ano, mes, dia);
				
				if(anexo != null) {
					File novoAnexo = new File("anexo_" + codigo + ".pdf");
					FileOutputStream output = new FileOutputStream(novoAnexo);
					
					byte[] buffer = new byte[1024];
					
					while (anexo.read(buffer) > 0) {
						output.write(buffer);
					}
					
					anexo.close();
					output.close();
					
					comenta.setAnexo(novoAnexo);
				}
				alu.setRa(fkAluno);
				admin.setEmail(fkAdm);
				solici.setIdSolicitacao(fkSolicitacao);
				
				comenta.setIdComentario(codigo);
				comenta.setTexto(texto);
				comenta.setDataHora(data);
				comenta.setAluno(alu);
				comenta.setAdministrador(admin);
				comenta.setSolicitacao(solici);
				
				comentarios.add(comenta);				
			}
			return comentarios;
			
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
