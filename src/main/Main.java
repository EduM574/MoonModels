package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
// import java.util.GregorianCalendar;

import model.*;
import service.*;

public class Main {
	public static void main(String args[]) {
		// AdministradorService admService = new AdministradorService();
		// AlunoService alnService = new AlunoService();

       //Insert de novos adm (email, setor, status) o status começa com INATIVO
       Setor setor = new Setor(1, "MASTER", "", "", null);
       Administrador adm = new Administrador("", "", "", "", "fulano2@usjt.br", "", setor, null, null, null);
    //    admService.create(adm);

       //Update em qualquer adm (todos os parâmetros exceto email)
    //    Setor setor2 = new Setor(2, "", "", "", null);
    //    Administrador adm2 = new Administrador("João", "Fulano", "ATIVO", "48997195824", "fulano2@usjt.br", "1234", setor2, null, null, null);
    //    admService.updateTotal(adm2);
// //
// //        //Update de primeiro acesso na plataforma (nome, sobrenome, cpf, senha, status) o status muda para ATIVO
// //        Administrador adm3 = new Administrador("João", "Fulano", "", "48997195824", "fulano2@usjt.br", "1234", null, null, null, null);
// //        admService.updateInicial(adm3);
// //
// //        //Update dentro da plataforma (senha)
// 		// Administrador adm4 = new Administrador("", "", "", "", "fulano2@usjt.br","12345", null, null, null, null);
// 		// admService.updateSenha(adm4);

// 			//Insert de novos alunos: nome, sobrenome, ra, status, cpf, data nasci, curso,
// 			//turno, unidade semestre, email, senha

// 			//Insert (todos os dados exceto ra) *a senha inicial vai ser o CPF do aluno, status inicial é **ATIVO** *
        // GregorianCalendar(int year, int month, int day);
        // GregorianCalendar dataNascimento = new GregorianCalendar(2000, 8, 1);
        // Aluno aln1 = new Aluno("Kex", "Fortuna", 0, "","56789", dataNascimento, "Arquitetura", "Manhã", "Mooca", 3, "Kessy@gmail.com", "", adm, null, null);
		// System.out.println(aln1.getRa());
		// alnService.create(aln1); 
        // System.out.println(aln1.getRa());
        
// 			//Update dentro da plataforma (senha)
//         // Aluno aln2 = new Aluno("", "", 2, "","33333",null,"", "", "", 0, "", "edu123", adm4,null,null);
//         // alnService.updateSenha(aln2);
			
// 			//Update feito pelo ADM (todos os dados exceto FK e RA)
//         //  Aluno aln3 = new Aluno("Dani", "Moreira", 2, "INATIVO","33333",null,"Direito ", "Manha", "Paulista", 4, "danimail@", "kex", adm4,null,null);
//         //  alnService.updateDoAdm(aln3);

//         //Update dentro da plataforma (senha)
//  //       Administrador adm4 = new Administrador("", "", "", "", "fulano2@usjt.br", "12345", null, null, null, null);
//  //       admService.updateSenha(adm4);
        
//          Aluno aluno1 = new Aluno("caio", "silva", 1, "indo", "35496197821", null, "computa��o", "noite", "paulista", 3, "caiogsilva@eumesmoeevai", "seraio", adm, null, null );
         SolicitacaoService soliService = new SolicitacaoService();
// //         //Create nova Solicitacao
// 		Solicitacao soli1 = new Solicitacao(0,"passe", "solicitacao de passe", null, "", null, 6, aluno1, null);
// 		System.out.println(soli1.getIdSolicitacao());
// 	   	soliService.create(soli1);
// 	   	System.out.println(soli1.getIdSolicitacao());
         
          Solicitacao soli2 = new Solicitacao(1, "", "", null , "pendente", null, 0, null, null);
//          //Atualiza a solicitação
//         //  soliService.update(soli2);
         
//          //Registra essa atualização
//         //  GerenciaAdmSolicitacao gerencia = new GerenciaAdmSolicitacao(adm, soli2, null, "Processo concluido com sucesso");
//         //  GerenciaAdmSolicitacaoService gerenciaService = new GerenciaAdmSolicitacaoService();
//         //  gerenciaService.create(gerencia);
         
          ComentarioService comService = new ComentarioService();
         
//         Comentario com1 = new Comentario(0, "o eu aqui.", null, null, aluno1, adm, soli2);
//         System.out.println(com1.getIdComentario());
//         comService.create(com1);
//         System.out.println(com1.getIdComentario());
         
//         //  Comentario com2 = new Comentario(1, "isso e update.",null,null,null,null,null);
//         //  comService.update(com2);
        
        //Todos os dados de todas as solicitações correspondentes a aquele aluno se estiver ativo e com aquele status (exibe pro aluno)
        // Aluno aln1 = new Aluno("Duts", "Silva", 2, "ATIVO","33333", null, "Letras", "Noite", "Paulista", 3, "edumail@", "", adm, null, null);
        // ArrayList<Solicitacao> solicitacoesAluno = soliService.selectSolicitacoesAluno(aln1, "pendente");

        // for(Solicitacao solicitacaoAluno : solicitacoesAluno) {
        //     System.out.println("Código: " + solicitacaoAluno.getIdSolicitacao());
        //     System.out.println("Nome: " + solicitacaoAluno.getNome());
        //     System.out.println("Descrição: " + solicitacaoAluno.getDescricao());
        //     System.out.println("Anexo: " + solicitacaoAluno.getAnexo());
        //     System.out.println("Status: " + solicitacaoAluno.getStatus());

        //     SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        //     String dataFormatada = sdf.format(solicitacaoAluno.getDataAbertura().getTime());
        //     System.out.println("Data de abertura: " + dataFormatada);

        //     System.out.println("Prazo: " + solicitacaoAluno.getPrazo());
        //     System.out.println("Código do aluno: " + solicitacaoAluno.getAluno().getRa());
        // }

        
//        ArrayList<Solicitacao> solicitacoesADM = soliService.selectSolicitacoesADM(adm);
//
//        for(Solicitacao solicitacaoADM : solicitacoesADM) {
//            System.out.println("Código: " + solicitacaoADM.getIdSolicitacao());
//            System.out.println("Nome: " + solicitacaoADM.getNome());
//            System.out.println("Descrição: " + solicitacaoADM.getDescricao());
//            System.out.println("Anexo: " + solicitacaoADM.getAnexo());
//            System.out.println("Status: " + solicitacaoADM.getStatus());
//
//            SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
//            String dataFormatada = sdf.format(solicitacaoADM.getDataAbertura().getTime());
//            System.out.println("Data de abertura: " + dataFormatada);
//
//            System.out.println("Prazo: " + solicitacaoADM.getPrazo());
//            System.out.println("Código do aluno: " + solicitacaoADM.getAluno().getRa() + "\n");
//        }
         
         ArrayList<Comentario> comentariosGeral = comService.selectComentariosDados(soli2);
         
         for(Comentario comentarios : comentariosGeral) {
           System.out.println("C�digo: " + comentarios.getIdComentario());
           System.out.println("Texto: " + comentarios.getTexto());
           System.out.println("Anexo: " + comentarios.getAnexo());

           SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
           String dataFormatada = sdf.format(comentarios.getDataHora().getTime());
           System.out.println("Data de abertura: " + dataFormatada);

           System.out.println("C�digo do aluno: " + comentarios.getAluno().getRa());
           System.out.println("Email do adm: " + comentarios.getAdministrador().getEmail());
           System.out.println("C�digo da Solicita��o: " + comentarios.getSolicitacao().getIdSolicitacao() + "\n");
       }
         
         
    }
}