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
                        + "VALUES (?, ?, ?, ?,'2000-01-19', ?, ?, ?, ?, ?, ?, 'fulano2@usjt.br')";

        try(PreparedStatement pst = conexao.prepareStatement(create)) {

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
            
            pst.execute();

        } catch(SQLException e) {
			System.err.println("Falha no banco: " + e.getMessage());
			e.printStackTrace();
		} catch ( Exception e) {
			System.err.println("Falha no java: " + e.getMessage());
			e.printStackTrace();
		}
    }
//    GregorianCalendar gc = new GregorianCalendar();
//    gc = aluno.getData_nascimento();
   // pst.setDate(5, (Date)gc.getTime());
//    public void updateTotalAdministrador(Administrador adm) {
//        String update = "UPDATE administrador SET nome = ?, sobrenome = ?, cpf = ?, "
//                        + "statusA = ?, senha = ?, fk_cod_setor = ? "
//                        + "WHERE email = ?";
//
//        try(PreparedStatement pst = conexao.prepareStatement(update)) {
//
//            pst.setString(1, adm.getNome());
//            pst.setString(2, adm.getSobrenome());
//            pst.setString(3, adm.getCpf());
//            pst.setString(4, adm.getStatus());
//            pst.setString(5, adm.getSenha());
//            pst.setInt(6, adm.getSetor().getIdSetor());
//            pst.setString(7, adm.getEmail());
//
//            pst.execute();
//
//        } catch(SQLException e) {
//			System.err.println("Falha no banco: " + e.getMessage());
//			e.printStackTrace();
//		} catch ( Exception e) {
//			System.err.println("Falha no java: " + e.getMessage());
//			e.printStackTrace();
//		}
//    }
//
//    public void updateInicialAdministrador(Administrador adm) {
//        String update = "UPDATE administrador SET nome = ?, sobrenome = ?, cpf = ?, "
//                        + "statusA = ?, senha = ? "
//                        + "WHERE email = ?";
//
//        try(PreparedStatement pst = conexao.prepareStatement(update)) {
//
//            pst.setString(1, adm.getNome());
//            pst.setString(2, adm.getSobrenome());
//            pst.setString(3, adm.getCpf());
//            pst.setString(4, adm.getStatus());
//            pst.setString(5, adm.getSenha());
//            pst.setString(6, adm.getEmail());
//
//            pst.execute();
//
//        } catch(SQLException e) {
//			System.err.println("Falha no banco: " + e.getMessage());
//			e.printStackTrace();
//		} catch ( Exception e) {
//			System.err.println("Falha no java: " + e.getMessage());
//			e.printStackTrace();
//		}
//    }
//
//    public void updateSenhaAdministrador(Administrador adm) {
//        String update = "UPDATE administrador SET senha = ? WHERE email = ?";
//
//        try(PreparedStatement pst = conexao.prepareStatement(update)) {
//
//            pst.setString(1, adm.getSenha());
//            pst.setString(2, adm.getEmail());
//
//            pst.execute();
//
//        } catch(SQLException e) {
//			System.err.println("Falha no banco: " + e.getMessage());
//			e.printStackTrace();
//		} catch ( Exception e) {
//			System.err.println("Falha no java: " + e.getMessage());
//			e.printStackTrace();
//		}
//    }
}