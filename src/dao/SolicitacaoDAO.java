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

	public ArrayList<Solicitacao> solicitacoesDeferidaIndeferidaAluno(Aluno aluno, String statusSolicitacao) {
		String consulta = "SELECT * FROM solicitacao WHERE fk_ra_aluno = ? AND statusS = ?;";
		
		try(PreparedStatement pst = conexao.prepareStatement(consulta)){
			
			pst.setInt(1, aluno.getRa());
			pst.setString(2, statusSolicitacao);
			
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

	public ArrayList<Solicitacao> solicitacoesAtivaAluno(Aluno aluno) {
		String consulta = "SELECT * FROM solicitacao WHERE fk_ra_aluno = ? AND statusS != 'DEFERIDA' AND statusS != 'INDEFERIDA' ";
		
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

	public ArrayList<Solicitacao> solicitacoesADM(String nomeSolicitacao) {
		//Captura todos os dados das solicitações com um nome especifico
		//que não foram concluindas(deferida/indereferida)
		//e que tenham alunos ativos
		//trazendo ordenadas pelas mais antigas(data de abertura)
		String consulta = "SELECT * FROM solicitacao AS S INNER JOIN aluno AS A "
						+ " ON S.nome = ? "
						+ " AND S.statusS != 'DEFERIDA' "
						+ " AND S.statusS != 'INDEFERIDA' "
						+ " AND A.statusA = 'ATIVO' "
						+ " ORDER BY data_abertura ASC;";
		
		try(PreparedStatement pst = conexao.prepareStatement(consulta)) {
			
			pst.setString(1, nomeSolicitacao);
			
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

	public ArrayList<Solicitacao> solicitacoesADMaster() {
		//Captura todos os dados de todas as solicitacoes
		//que não foram concluindas(deferida/indereferida)
		//e que tenham alunos ativos
		//trazendo ordenadas pelas mais antigas(data de abertura)
		String consulta = "SELECT S.* FROM solicitacao AS S "
						+ "WHERE S.statusS != 'DEFERIDA' "
						+ "AND S.statusS != 'INDEFERIDA' "
        				+ "AND fk_ra_aluno IN (SELECT ra FROM aluno WHERE statusA = 'ATIVO') "
						+ "ORDER BY data_abertura ASC;";
		
		try(PreparedStatement pst = conexao.prepareStatement(consulta)) {
						
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
