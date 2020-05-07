package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Administrador;
import model.Setor;
import model.Validation;

public class AdministradorDAO {
	private Connection conexao;

	public AdministradorDAO() {
		this(null);
	}

	public AdministradorDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public void createAdminitrador(Administrador adm) {
		String create = "INSERT INTO administrador(nome, sobrenome, cpf, statusA, email, senha, fk_cod_setor)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pst = conexao.prepareStatement(create)) {

			pst.setString(1, adm.getNome());
			pst.setString(2, adm.getSobrenome());
			pst.setString(3, adm.getCpf());
			pst.setString(4, adm.getStatus());
			pst.setString(5, adm.getEmail());
			pst.setString(6, adm.getSenha());
			pst.setInt(7, adm.getSetor().getIdSetor());

			pst.execute();

		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void updateTotalAdministrador(Administrador adm) {
		String update = "UPDATE administrador SET nome = ?, sobrenome = ?, cpf = ?, "
				+ "statusA = ?, senha = ?, fk_cod_setor = ? " + "WHERE email = ?";

		try (PreparedStatement pst = conexao.prepareStatement(update)) {

			pst.setString(1, adm.getNome());
			pst.setString(2, adm.getSobrenome());
			pst.setString(3, adm.getCpf());
			pst.setString(4, adm.getStatus());
			pst.setString(5, adm.getSenha());
			pst.setInt(6, adm.getSetor().getIdSetor());
			pst.setString(7, adm.getEmail());

			pst.execute();

		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void updateSenhaAdministrador(Administrador adm) {
		String update = "UPDATE administrador SET senha = ? WHERE email = ?";

		try (PreparedStatement pst = conexao.prepareStatement(update)) {

			pst.setString(1, adm.getSenha());
			pst.setString(2, adm.getEmail());

			pst.execute();

		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public Administrador adminGeral(Administrador adm) {
		String consulta = "SELECT * FROM administrador WHERE email = ?;";

		try (PreparedStatement pst = conexao.prepareStatement(consulta)) {

			pst.setString(1, adm.getEmail());

			ResultSet resultado = pst.executeQuery();

			Administrador admini = new Administrador();
			Setor set = new Setor();

			if (resultado.next()) {

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

		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Administrador> adminOrder() {
		String consulta = "SELECT * FROM administrador ORDER BY nome ASC;";

		try (PreparedStatement pst = conexao.prepareStatement(consulta)) {

			ResultSet resultado = pst.executeQuery();

			ArrayList<Administrador> admin2 = new ArrayList<Administrador>();

			while (resultado.next()) {
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

		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public Validation createValidation(Administrador adm) {
		String consulta = "SELECT * FROM administrador WHERE cpf = ?;";
		Validation v = new Validation();

		try (PreparedStatement pst = conexao.prepareStatement(consulta)) {

			pst.setString(1, adm.getCpf());
			ResultSet resultado = pst.executeQuery();

			if (resultado.next()) {
				// caso encontre alguem com esse cpf o cadastro nao pode ser feito
				// pois a pessoa ja esta registrada no sistema
				v.setStatus(true);
				v.setText("Este usuário já existe no sistema, não é possivel cadastra-lo novamente");
			} else {
				// caso não encontre alguem com esse cpf, verificar o email
				String consulta2 = "SELECT * FROM administrador WHERE email = ?;";
				
				try (PreparedStatement pst2 = conexao.prepareStatement(consulta2)) {
					
					pst2.setString(1, adm.getEmail());
					
					ResultSet resultado2 = pst2.executeQuery();
					
					if (resultado2.next()) {
						// caso encontre alguem com esse email deve escolher outro
						v.setStatus(true);
						v.setText("Já existe um administrador com esse e-mail cadastrado no banco, escolha outro.");
						
					} else {
						// caso nao encontre alguem com esse email e cpf o cadastro pode ser feito
						v.setStatus(false);
						v.setText("");
					}

				} catch (SQLException e) {
					System.err.println("Falha no banco: " + e.getMessage());
					e.printStackTrace();
				} catch (Exception e) {
					System.err.println("Falha no java: " + e.getMessage());
					e.printStackTrace();
				}
			}

			return v;

		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public Validation updateValidation(Administrador adm) {
		String consulta = "SELECT * FROM administrador WHERE cpf = ? AND email !=;";
		Validation v = new Validation();

		try (PreparedStatement pst = conexao.prepareStatement(consulta)) {

			pst.setString(1, adm.getCpf());
			pst.setString(2, adm.getEmail());

			ResultSet resultado = pst.executeQuery();

			if (resultado.next()) {
				// caso encontre alguem com esse cpf o update nao pode ser feito
				//pois ja existe um outro user com esse CPF cadastrado
				v.setStatus(true);
				v.setText("Não foi possivel realizar a alteração, já exite um outro administrador com esse CPF no sistema.");
			} else {
				v.setStatus(false);
				v.setText("");
			}

			return v;

		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public Validation loginValidation(Administrador adm) {
		String consulta = "SELECT * FROM administrador WHERE email = ? " + " AND senha = ? AND statusA = 'ATIVO';";
		Validation v = new Validation();

		try (PreparedStatement pst = conexao.prepareStatement(consulta)) {

			pst.setString(1, adm.getEmail());
			pst.setString(2, adm.getSenha());
			ResultSet resultado = pst.executeQuery();

			if (resultado.next()) {
				v.setStatus(false);
				v.setText("");
			} else {
				v.setStatus(true);
				v.setText("Acesso negado para essas credenciais. Verifique seu e-mail e senha.");
			}

			return v;

		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
}