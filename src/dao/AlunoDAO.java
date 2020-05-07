package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.Administrador;
import model.Aluno;
import model.Validation;

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
		String update = "UPDATE aluno SET nome= ?, sobrenome= ?, statusA= ?, cpf= ?, data_nascimento= ?, curso= ?, turno= ?, unidade= ?, semestre= ?, email= ?, senha= ? WHERE ra = ? ";

		try (PreparedStatement pst = conexao.prepareStatement(update)) {
			
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
			pst.setInt(12, aluno.getRa());

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
				
				String data = resultado.getString("data_nascimento");
				String[] dataSeparada = data.split("-");
				int ano = Integer.parseInt(dataSeparada[0]);
				int mes = Integer.parseInt(dataSeparada[1]);
				int dia = Integer.parseInt(dataSeparada[2]);				
				GregorianCalendar dataNasc = new GregorianCalendar(ano, mes, dia);

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
				
    			String data = resultado.getString("data_nascimento");
				String[] dataSeparada = data.split("-");
				int ano = Integer.parseInt(dataSeparada[0]);
				int mes = Integer.parseInt(dataSeparada[1]);
				int dia = Integer.parseInt(dataSeparada[2]);				
				GregorianCalendar dataNasc = new GregorianCalendar(ano, mes, dia);
				
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
	
	public Validation createValidation(Aluno aluno) {
		String consulta = "SELECT * FROM aluno WHERE cpf = ?;";
		Validation v = new Validation();

		try(PreparedStatement pst = conexao.prepareStatement(consulta)){
			
			pst.setString(1, aluno.getCpf());
			ResultSet resultado = pst.executeQuery();

			if(resultado.next()) {
				//caso encontre um registro com o cpf
				v.setStatus(true);
            	v.setText("Aluno já cadastrado no sistema");

			} else {
				//caso nao encontre registro com esse cpf, verificar email
				String consulta2 = "SELECT * FROM aluno WHERE email = ?;";

				try(PreparedStatement pst2 = conexao.prepareStatement(consulta2)){

					pst2.setString(1, aluno.getEmail());

					ResultSet resultado2 = pst2.executeQuery();

					if(resultado2.next()) {
						//caso encontre alguem com esse email o ADM deverá escolher outro
						v.setStatus(true);
						v.setText("Já existe um usuário registrado com esse e-mail, por favor escolha outro");
					} else {
						//caso nao encontre alguem com esse email, o cadastro pode ser concluido
						v.setStatus(false);
						v.setText("");
					}
				
				} catch(SQLException e) {
					System.err.println("Falha no banco: " + e.getMessage());
					e.printStackTrace();
				} catch( Exception e) {
					System.err.println("Falha no java: " + e.getMessage());
					e.printStackTrace();
				}
			}

			return v;
			
		} catch(SQLException e) {
    		System.err.println("Falha no banco: " + e.getMessage());
    		e.printStackTrace();
    	} catch( Exception e) {
    		System.err.println("Falha no java: " + e.getMessage());
    		e.printStackTrace();
    	}
		
		return null;
	}

	public Validation updateValidation(Aluno aluno) {
		String consulta = "SELECT * FROM aluno WHERE cpf = ?;";
		Validation v = new Validation();

		try(PreparedStatement pst = conexao.prepareStatement(consulta)){
			
			pst.setString(1, aluno.getCpf());
			ResultSet resultado = pst.executeQuery();

			if(resultado.next()) {
				//caso encontre um registro com o cpf
				v.setStatus(true);
            	v.setText("Não foi possivel realizar a alteração, já exite um aluno com esse CPF no sistema.");

			} else {
				//caso nao encontre registro com esse cpf, verificar email
				String consulta2 = "SELECT * FROM aluno WHERE email = ?;";

				try(PreparedStatement pst2 = conexao.prepareStatement(consulta2)){

					pst2.setString(1, aluno.getEmail());

					ResultSet resultado2 = pst2.executeQuery();

					if(resultado2.next()) {
						//caso encontre alguem com esse email o ADM deverá escolher outro
						v.setStatus(true);
						v.setText("Não foi possivel realizar a alteração, já exite um aluno com esse e-mail no sistema.");
					} else {
						//caso nao encontre alguem com esse email, o cadastro pode ser concluido
						v.setStatus(false);
						v.setText("");
					}
				
				} catch(SQLException e) {
					System.err.println("Falha no banco: " + e.getMessage());
					e.printStackTrace();
				} catch( Exception e) {
					System.err.println("Falha no java: " + e.getMessage());
					e.printStackTrace();
				}
			}

			return v;
			
		} catch(SQLException e) {
    		System.err.println("Falha no banco: " + e.getMessage());
    		e.printStackTrace();
    	} catch( Exception e) {
    		System.err.println("Falha no java: " + e.getMessage());
    		e.printStackTrace();
    	}
		
		return null;
	}

	public Validation loginValidation(Aluno aluno) {
		String consulta = "SELECT * FROM aluno WHERE ra = ? "
						+" AND senha = ? AND statusA = 'ATIVO';";
		Validation v = new Validation();

		try(PreparedStatement pst = conexao.prepareStatement(consulta)){
			
			pst.setInt(1, aluno.getRa());
			pst.setString(2, aluno.getSenha());
			ResultSet resultado = pst.executeQuery();

			if(resultado.next()) {
				v.setStatus(false);
            	v.setText("");
			} else {
				v.setStatus(true);
            	v.setText("Acesso negado para essas credenciais. Verifique seu RA e senha");
			}

			return v;
			
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