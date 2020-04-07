package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.io.*;
import model.Solicitacao;
import model.Aluno;

public class SolicitacaoDAO {
	private Connection conexao;
	
	public SolicitacaoDAO() {
		this(null);
	}
	
	public SolicitacaoDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public int createSolicitacao(Solicitacao solicitacao) {
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
			
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conexao.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					solicitacao.setIdSolicitacao(rs.getInt(1));
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

		return solicitacao.getIdSolicitacao();
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

	public ArrayList<Solicitacao> solicitacoesAluno(Aluno aluno) {
		String consulta = "SELECT * FROM solicitacao WHERE fk_ra_aluno = ?;";
		
		try(PreparedStatement pst = conexao.prepareStatement(consulta)){
			
			pst.setInt(1, aluno.getRa());
			
			ResultSet resultado = pst.executeQuery();
			
			ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();

			while(resultado.next()) {
				Solicitacao solicita = new Solicitacao();
				Aluno al = new Aluno();
			
				int codigo = resultado.getInt("codigo");
				String nome = resultado.getString("nome");
				String descricao = resultado.getString("descricao");
				String status = resultado.getString("statusS");
				int prazo = resultado.getInt("prazo");
				int fkAluno = resultado.getInt("fk_ra_aluno");
				InputStream anexo = resultado.getBinaryStream("anexo");
				GregorianCalendar data = new GregorianCalendar();

				data.setTime(new java.util.Date(resultado.getTimestamp("data_abertura").getTime()));
					
				if(anexo != null) {
					File novoAnexo = new File("anexo_" + codigo + ".pdf");
					FileOutputStream output = new FileOutputStream(novoAnexo);
					
					byte[] buffer = new byte[1024];
					// Enquanto existir conteúdo no fluxo de dados, continua:
					while (anexo.read(buffer) > 0) {
						// Escreve o conteúdo no arquivo de destino no disco:
						output.write(buffer);
					}
	
					// Fechando a entrada:
					anexo.close();
	
					// Encerra a saída:
					output.close();

					solicita.setAnexo(novoAnexo);
				}
				
				al.setRa(fkAluno);
				
				solicita.setIdSolicitacao(codigo);
				solicita.setNome(nome);
				solicita.setDescricao(descricao);
				solicita.setStatus(status);
				solicita.setDataAbertura(data);
				solicita.setPrazo(prazo);
				solicita.setAluno(al);
				
				solicitacoes.add(solicita);
			}
			
			return solicitacoes;

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
