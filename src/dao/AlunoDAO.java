package dao;

import java.sql.*;
import java.util.ArrayList;
// import java.util.GregorianCalendar;
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
	
	public Aluno alunoBusca(Aluno aluno){
    	String consulta = "SELECT * FROM aluno WHERE ra = ?;";
    	
    	try(PreparedStatement pst = conexao.prepareStatement(consulta)) {
    		
    		pst.setInt(1, aluno.getRa());
    		
    		ResultSet resultado = pst.executeQuery();
    		
			Aluno aluno1= new Aluno();
			Administrador adm = new Administrador();
    		if(resultado.next()) {
    			
    			String nome = resultado.getString("nome");
				String sobrenome = resultado.getString("sobrenome");
				int ra = resultado.getInt("ra");
    			String cpf = resultado.getString("cpf");
    			String statusA = resultado.getString("statusA");
    			GregorianCalendar dataNasc = new GregorianCalendar();
    			dataNasc.setTime(new java.util.Date(resultado.getTimestamp("data_nascimento").getTime()));
    			String curso = resultado.getString("curso");
    			String turno = resultado.getString("turno");
    			String unidade = resultado.getString("unidade");
    			int semestre = resultado.getInt("semestre");
    			String email = resultado.getString("email");
    			String senha = resultado.getString("senha");
    			String emailAdm = resultado.getString("fk_email_adm");
    			
    			aluno1.setNome(nome);
				aluno1.setSobrenome(sobrenome);
				aluno1.setRa(ra);
    			aluno1.setCpf(cpf);
    			aluno1.setStatus(statusA);
    			aluno1.setData_nascimento(dataNasc);
    			aluno1.setCurso(curso);
    			aluno1.setTurno(turno);
    			aluno1.setUnidade(unidade);
    			aluno1.setSemestre(semestre);
    			aluno1.setEmail(email);
    			aluno1.setSenha(senha);
    			aluno1.setAdm(adm);
    			aluno1.getAdm().setEmail(emailAdm);
    			
    		}
    		
    		return aluno1;
    		
    	} catch(SQLException e) {
    		System.err.println("Falha no banco: " + e.getMessage());
    		e.printStackTrace();
    	} catch( Exception e) {
    		System.err.println("Falha no java: " + e.getMessage());
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public ArrayList<Aluno> alunoOrder(){
    	String consulta = "SELECT * FROM aluno ORDER BY nome ASC;";
    	
    	try(PreparedStatement pst = conexao.prepareStatement(consulta)){
    		
    		ResultSet resultado = pst.executeQuery();
    		
    		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
    		
    		while(resultado.next()) {
    			Aluno aluno1 = new Aluno(); 
    			Administrador adm = new Administrador();
    			String nome = resultado.getString("nome");
				String sobrenome = resultado.getString("sobrenome");
				int ra = resultado.getInt("ra");
    			String cpf = resultado.getString("cpf");
    			String statusA = resultado.getString("statusA");
    			GregorianCalendar dataNasc = new GregorianCalendar();
    			dataNasc.setTime(new java.util.Date(resultado.getTimestamp("data_nascimento").getTime()));
    			String curso = resultado.getString("curso");
    			String turno = resultado.getString("turno");
    			String unidade = resultado.getString("unidade");
    			int semestre = resultado.getInt("semestre");
    			String email = resultado.getString("email");
    			String senha = resultado.getString("senha");
    			String emailAdm = resultado.getString("fk_email_adm");
    			
    			aluno1.setNome(nome);
				aluno1.setSobrenome(sobrenome);
				aluno1.setRa(ra);
    			aluno1.setCpf(cpf);
    			aluno1.setStatus(statusA);
    			aluno1.setData_nascimento(dataNasc);
    			aluno1.setCurso(curso);
    			aluno1.setTurno(turno);
    			aluno1.setUnidade(unidade);
    			aluno1.setSemestre(semestre);
    			aluno1.setEmail(email);
    			aluno1.setSenha(senha);
    			aluno1.setAdm(adm);
    			aluno1.getAdm().setEmail(emailAdm);
    			
    			alunos.add(aluno1);
    		}
    		
    		return alunos;
    		
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