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
		
		String create = "";
		if(comentario.getAluno() != null) {
			create = "INSERT INTO comentario(texto, data_hora, anexo, fk_ra_aluno, fk_codigo_solicitacao)" + "VALUES (?, now(), ?, ?, ?)";
		} else {
			create = "INSERT INTO comentario(texto, data_hora, anexo, fk_email_adm, fk_codigo_solicitacao)" + "VALUES (?, now(), ?, ?, ?)";
		}
		
		try(PreparedStatement pst = conexao.prepareStatement(create)){

			pst.setString(1, comentario.getTexto());
			pst.setInt(4, comentario.getSolicitacao().getIdSolicitacao());

			if(comentario.getAluno() != null) {
				pst.setInt(3, comentario.getAluno().getRa());
			} else {
				pst.setString(3, comentario.getAdministrador().getEmail());
			}
			
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
		String consulta1 = "SELECT C.*, Al.nome, Al.sobrenome "
						+ "FROM comentario AS C "
						+ "INNER JOIN aluno AS Al "
						+ "ON C.fk_ra_aluno = Al.ra "
						+ "WHERE C.fk_codigo_solicitacao = ? "
						+ "ORDER BY C.data_hora ASC;";
		
		String consulta2 = "SELECT C.*, Ad.nome, Ad.sobrenome "
						+ "FROM comentario AS C "
						+ "INNER JOIN administrador AS Ad "
						+ "ON C.fk_email_adm = Ad.email "
						+ "WHERE C.fk_codigo_solicitacao = ? "
						+ "ORDER BY C.data_hora ASC;";

		ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
		try(PreparedStatement pst = conexao.prepareStatement(consulta1)){
			
			pst.setInt(1, solicitacao.getIdSolicitacao());
			
			ResultSet resultado = pst.executeQuery();	
			
			while(resultado.next()) {
				Comentario comenta = new Comentario();
				Aluno alu = new Aluno();
				Solicitacao solici = new Solicitacao();
				
				int codigo =  resultado.getInt("codigo");
				String texto = resultado.getString("texto");
				int fkAluno = resultado.getInt("fk_ra_aluno");
				int fkSolicitacao = resultado.getInt("fk_codigo_solicitacao");
				InputStream anexo = resultado.getBinaryStream("anexo");
				String nome = resultado.getString("nome");
				String sobrenome = resultado.getString("sobrenome");
				
				String dataBanco = resultado.getString("data_hora");
				
				String[] dataBancoSeparada = dataBanco.split(" ");
				String dataCompleta = dataBancoSeparada[0];
				String horaCompleta = dataBancoSeparada[1];

				String[] dataSeparada = dataCompleta.split("-");
				int ano = Integer.parseInt(dataSeparada[0]);
				int mes = Integer.parseInt(dataSeparada[1]);
				int dia = Integer.parseInt(dataSeparada[2]);

				String[] horaSeparada = horaCompleta.split(":");
				int hora = Integer.parseInt(horaSeparada[0]);
				int minuto = Integer.parseInt(horaSeparada[1]);
				int segundo = Integer.parseInt(horaSeparada[2]);

				GregorianCalendar dataFinal = new GregorianCalendar(ano, mes, dia, hora, minuto, segundo);
				
				if(anexo != null) {
					File novoAnexo = new File("C:/Users/kesse/OneDrive/Documents/USJT/3º semestre/PI/MoonModels/WebContent/anexoSolicitacoes/comentario" + codigo + ".pdf");
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
				alu.setNome(nome);
				alu.setSobrenome(sobrenome);
				solici.setIdSolicitacao(fkSolicitacao);
				
				comenta.setIdComentario(codigo);
				comenta.setTexto(texto);
				comenta.setDataHora(dataFinal);
				comenta.setAluno(alu);
				comenta.setSolicitacao(solici);
				
				comentarios.add(comenta);				
			}
			
		} catch(SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch( Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}

		try(PreparedStatement pst = conexao.prepareStatement(consulta2)){
			
			pst.setInt(1, solicitacao.getIdSolicitacao());
			
			ResultSet resultado = pst.executeQuery();	
			
			while(resultado.next()) {
				Comentario comenta = new Comentario();
				Administrador adm = new Administrador();
				Solicitacao solici = new Solicitacao();
				
				int codigo =  resultado.getInt("codigo");
				String texto = resultado.getString("texto");
				String fkAdm = resultado.getString("fk_email_adm");
				int fkSolicitacao = resultado.getInt("fk_codigo_solicitacao");
				InputStream anexo = resultado.getBinaryStream("anexo");
				String nome = resultado.getString("nome");
				String sobrenome = resultado.getString("sobrenome");
				
				String dataBanco = resultado.getString("data_hora");
				
				String[] dataBancoSeparada = dataBanco.split(" ");
				String dataCompleta = dataBancoSeparada[0];
				String horaCompleta = dataBancoSeparada[1];

				String[] dataSeparada = dataCompleta.split("-");
				int ano = Integer.parseInt(dataSeparada[0]);
				int mes = Integer.parseInt(dataSeparada[1]);
				int dia = Integer.parseInt(dataSeparada[2]);

				String[] horaSeparada = horaCompleta.split(":");
				int hora = Integer.parseInt(horaSeparada[0]);
				int minuto = Integer.parseInt(horaSeparada[1]);
				int segundo = Integer.parseInt(horaSeparada[2]);

				GregorianCalendar dataFinal = new GregorianCalendar(ano, mes, dia, hora, minuto, segundo);
				
				if(anexo != null) {
					File novoAnexo = new File("C:/Users/kesse/OneDrive/Documents/USJT/3º semestre/PI/MoonModels/WebContent/anexoSolicitacoes/comentario" + codigo + ".pdf");
					FileOutputStream output = new FileOutputStream(novoAnexo);
					
					byte[] buffer = new byte[1024];
					
					while (anexo.read(buffer) > 0) {
						output.write(buffer);
					}
					
					anexo.close();
					output.close();
					
					comenta.setAnexo(novoAnexo);
				}

				adm.setEmail(fkAdm);
				adm.setNome(nome);
				adm.setSobrenome(sobrenome);
				solici.setIdSolicitacao(fkSolicitacao);
				
				comenta.setIdComentario(codigo);
				comenta.setTexto(texto);
				comenta.setDataHora(dataFinal);
				comenta.setAdministrador(adm);
				comenta.setSolicitacao(solici);
				
				comentarios.add(comenta);
			}
			
		} catch(SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch( Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
		
		return comentarios;
	}
	
}
