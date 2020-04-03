package dao;

import java.sql.*;
import java.util.GregorianCalendar;

import model.Administrador;
import model.Aluno;

public class AlunoDAO {
	private Connection conexao;

	public AlunoDAO() {
		this(null);
	}

	public AlunoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public void createAluno(Aluno aluno) {
		String create = "INSERT INTO aluno(nome, sobrenome, statusA, cpf,data_nascimento, curso, turno, unidade, semestre, email, senha,fk_email_adm)"
				+ "VALUES (?, ?, ?, ?,'2000-01-19', ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pst = conexao.prepareStatement(create)) {

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
			pst.setString(11, aluno.getAdm().getEmail());

			pst.execute();

		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
	}
//    GregorianCalendar gc = new GregorianCalendar();
//    gc = aluno.getData_nascimento();
	// pst.setDate(5, (Date)gc.getTime());

	public void updateSenhaAluno(Aluno aluno) {
		String update = "UPDATE aluno SET senha = ? WHERE cpf=? and cpf=senha";

		try (PreparedStatement pst = conexao.prepareStatement(update)) {
			if (aluno.getSenha().equals(aluno.getCpf())) {
				System.out.println("Mesma senha de criação");
			} else if (aluno.getSenha().equals(null) || aluno.getCpf().equals(null)) {
				System.out.println("Senha nova não inserida");
			} else {
				pst.setString(1, aluno.getSenha());
				pst.setString(2, aluno.getCpf());
//            pst.setString(3, aluno.getCpf());
				pst.execute();
			}
		} catch (SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void updateDoAdmAluno(Aluno aluno) {
		String update = "UPDATE aluno SET nome= ?, sobrenome= ?, statusA= ?, cpf= ?, data_nascimento= '2000-01-10', curso= ?, turno= ?, unidade= ?, semestre= ?, email= ?, senha= ? WHERE (fk_email_adm = ? )";

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
			pst.setString(11, aluno.getAdm().getEmail());

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