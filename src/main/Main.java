package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.Administrador;
import model.Aluno;
import model.Setor;
import service.AdministradorService;
import service.AlunoService;

public class Main {
	public static void main(String args[]) {
		AdministradorService admService = new AdministradorService();
		AlunoService alnService = new AlunoService();

//        //Insert de novos adm (email, setor, status) o status começa com INATIVO
//        Setor setor = new Setor(1, "", "", "", null);
//        Administrador adm = new Administrador("", "", "", "", "fulano2@usjt.br", "", setor, null, null, null);
//        admService.create(adm);
//
//        //Update em qualquer adm (todos os parâmetros exceto email)
//        Setor setor2 = new Setor(2, "", "", "", null);
//        Administrador adm2 = new Administrador("João", "Fulano", "ATIVO", "48997195824", "fulano2@usjt.br", "1234", setor2, null, null, null);
//        admService.updateTotal(adm2);
//
//        //Update de primeiro acesso na plataforma (nome, sobrenome, cpf, senha, status) o status muda para ATIVO
//        Administrador adm3 = new Administrador("João", "Fulano", "", "48997195824", "fulano2@usjt.br", "1234", null, null, null, null);
//        admService.updateInicial(adm3);
//
//        //Update dentro da plataforma (senha)
//		  Administrador adm4 = new Administrador("", "", "", "", "fulano2@usjt.br","12345", null, null, null, null);
//        admService.updateSenha(adm4);

		// Insert de novos alunos: nome, sobrenome, ra, status, cpf, data nasci, curso,
		// turno, unidade semestre, email, senha

//         Aluno aln1 = new Aluno("Duts", "Silva", 0, "","33333",null,"Letras", "Noite", "Paulista", 3, "edumail@", "", adm4,null,null);        
//         alnService.create(aln1); 

//         Aluno aln2 = new Aluno("", "", 0, "","33333",null,"", "", "", 0, "", "edu123", adm4,null,null);
//         alnService.updateSenha(aln2);

//          Aluno aln3 = new Aluno("Dani", "Moreira", 0, "","33333",null,"Direito ", "Manh�", "Paulista", 4, "danimail@", "edu123", adm4,null,null);
//          alnService.updateDoAdm(aln3);

	}
}