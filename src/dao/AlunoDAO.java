package dao;

import java.sql.*;
// import java.util.GregorianCalendar;
import java.util.GregorianCalendar;

import model.Aluno;

public class AlunoDAO {
	private Connection conexao;

	public AlunoDAO() {
		this(null);
	}

	public AlunoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public int createAluno(Aluno aluno) {
		String create = "INSERT INTO aluno(nome, sobrenome, statusA, cpf,data_nascimento, curso, turno, unidade, semestre, email, senha,fk_email_adm)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pst = conexao.prepareStatement(create)) {
			String data = aluno.getData_nascimento().get(GregorianCalendar.YEAR) + "-"
						+ aluno.getData_nascimento().get(GregorianCalendar.MONTH) + "-"
						+ aluno.getData_nascimento().get(GregorianCalendar.DAY_OF_MONTH);
			
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getSobrenome());
			pst.setString(3, aluno.getStatus());
			pst.setString(4, aluno.getCpf());
			pst.setString(5, data);
			pst.setString(6, aluno.getCurso());
			pst.setString(7, aluno.getTurno());
			pst.setString(8, aluno.getUnidade());
			pst.setInt(9, aluno.getSemestre());
			pst.setString(10, aluno.getEmail());
			pst.setString(11, aluno.getSenha());
			pst.setString(12, aluno.getAdm().getEmail());

			pst.execute();

			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conexao.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					aluno.setRa(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}

		return aluno.getRa();
	}
//    GregorianCalendar gc = new GregorianCalendar();
//    gc = aluno.getData_nascimento();
	// pst.setDate(5, (Date)gc.getTime());

	public void updateSenhaAluno(Aluno aluno) {
		String update = "UPDATE aluno SET senha = ? WHERE ra=?";

		try (PreparedStatement pst = conexao.prepareStatement(update)) {
			
			pst.setString(1, aluno.getSenha());
			pst.setInt(2, aluno.getRa());
			pst.execute();
			
		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void updateDoAdmAluno(Aluno aluno) {
		String update = "UPDATE aluno SET nome= ?, sobrenome= ?, statusA= ?, cpf= ?, data_nascimento= '2000-01-10', curso= ?, turno= ?, unidade= ?, semestre= ?, email= ?, senha= ? WHERE ra = ? ";

		try (PreparedStatement pst = conexao.prepareStatement(update)) {

			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getSobrenome());
			pst.setString(3, aluno.getStatus());
			pst.setString(4, aluno.getCpf());
			pst.setString(5, aluno.getCurso());
			pst.setString(6, aluno.getTurno());
			pst.setString(7, aluno.getUnidade());
			pst.setInt(8, aluno.getSemestre());
			pst.setString(9, aluno.getEmail());
			pst.setString(10, aluno.getSenha());
			pst.setInt(11, aluno.getRa());

			pst.execute();

		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
	}
}