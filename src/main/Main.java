package main;

// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.*;
import service.*;

public class Main {
    public static void main(String args[]) {
        // AdministradorService admService = new AdministradorService();
        AlunoService alnService = new AlunoService();
        // SetorService setService = new SetorService();
        // Insert de novos adm (email, setor, status) o status começa com INATIVO
        Setor setor = new Setor(1, "MASTER", "", "", null);
        Administrador adm = new Administrador("", "", "", "4567876", "maria@usjt.br", "876", setor, null, null, null);
        // admService.create(adm);

        // Setor setor2 = new Setor(2, "Sptrans", "� nois", "", null);
        // Administrador adm5 = new Administrador("James", "Carter", "ATIVO",
        // "35496279821", "fulano5@usjt.br", "senha�essa", setor2, null, null, null);
        // admService.updateTotal(adm5);

        // Setor setor3 = new Setor(3, "Estagio", "� isso", "", null);
        // Administrador adm6 = new Administrador("Karim", "Abdhul", "ATIVO",
        // "35496279621", "fulano6@usjt.br", "senha�essaai", setor3, null, null, null);
        // admService.updateTotal(adm6);

        // Update em qualquer adm (todos os parâmetros exceto email)
        // Setor setor2 = new Setor(2, "", "", "", null);
        // Administrador adm2 = new Administrador("João", "Fulano", "ATIVO",
        // "48997195824", "fulano2@usjt.br", "1234", setor2, null, null, null);
        // admService.updateTotal(adm2);

        // // //Update de primeiro acesso na plataforma (nome, sobrenome, cpf, senha,
        // status) o status muda para ATIVO
        // // Administrador adm3 = new Administrador("João", "Fulano", "",
        // "48997195824", "fulano2@usjt.br", "1234", null, null, null, null);
        // // admService.updateInicial(adm3);
        // //
        // // //Update dentro da plataforma (senha)
        // // Administrador adm4 = new Administrador("", "", "", "",
        // "fulano2@usjt.br","12345", null, null, null, null);
        // // admService.updateSenha(adm4);

        // //Insert de novos alunos: nome, sobrenome, ra, status, cpf, data nasci,
        // curso,
        // //turno, unidade semestre, email, senha

        // //Insert (todos os dados exceto ra) *a senha inicial vai ser o CPF do aluno,
        // status inicial é **ATIVO** *
        // GregorianCalendar(int year, int month, int day);
        GregorianCalendar dataNascimento = new GregorianCalendar(2000, 8, 1);
        Aluno aln1 = new Aluno("Kex3", "Fortuna", 0, "", "234562278", dataNascimento, "Arquitetura", "Manhã", "Mooca", 3,
                "kess@gmail.com.br", "", adm, null, null);
        // System.out.println(aln1.getRa());
        // alnService.create(aln1);
        // System.out.println(aln1.getRa());

        // //Update dentro da plataforma (senha)
        // // Aluno aln2 = new Aluno("", "", 2, "","33333",null,"", "", "", 0, "",
        // "edu123", adm4,null,null);
        // // alnService.updateSenha(aln2);

        // //Update feito pelo ADM (todos os dados exceto FK e RA)
        // // Aluno aln3 = new Aluno("Dani", "Moreira", 2,
        // "INATIVO","33333",null,"Direito ", "Manha", "Paulista", 4, "danimail@",
        // "kex", adm4,null,null);
        // // alnService.updateDoAdm(aln3);

        // //Update dentro da plataforma (senha)
        // // Administrador adm4 = new Administrador("", "", "", "", "fulano2@usjt.br",
        // "12345", null, null, null, null);
        // // admService.updateSenha(adm4);

        // Aluno aluno1 = new Aluno("caio", "silva", 1, "indo", "35496197821", null,
        // "computa��o", "noite", "paulista", 3, "caiogsilva@eumesmoeevai", "seraio",
        // adm, null, null );
        // SolicitacaoService soliService = new SolicitacaoService();
        // //Create nova Solicitacao
        // Solicitacao soli1 = new Solicitacao(0,"passe", "solicitacao de passe", null,
        // "", null, 6, aluno1, null);
        // System.out.println(soli1.getIdSolicitacao());
        // soliService.create(soli1);
        // System.out.println(soli1.getIdSolicitacao());

        // Solicitacao soli2 = new Solicitacao(2, "", "", null , "pendente", null, 0,
        // null, null);
        // //Atualiza a solicitação
        // // soliService.update(soli2);

        // //Registra essa atualização
        // // GerenciaAdmSolicitacao gerencia = new GerenciaAdmSolicitacao(adm, soli2,
        // null, "Processo concluido com sucesso");
        // // GerenciaAdmSolicitacaoService gerenciaService = new
        // GerenciaAdmSolicitacaoService();
        // // gerenciaService.create(gerencia);

        // ComentarioService comService = new ComentarioService();

        // Comentario com1 = new Comentario(0, "o eu aqui.", null, null, aluno1, adm,
        // soli2);
        // System.out.println(com1.getIdComentario());
        // comService.create(com1);
        // System.out.println(com1.getIdComentario());

        // // Comentario com2 = new Comentario(1, "isso e
        // update.",null,null,null,null,null);
        // // comService.update(com2);

        // Todos os dados de todas as solicitações correspondentes a aquele aluno se
        // estiver ativo e com aquele status (exibe pro aluno)
        // Aluno aln1 = new Aluno("Duts", "Silva", 2, "ATIVO","33333", null, "Letras",
        // "Noite", "Paulista", 3, "edumail@", "", adm, null, null);
        // ArrayList<Solicitacao> solicitacoesAluno =
        // soliService.selectSolicitacoesAluno(aln1, "pendente");

        // for(Solicitacao solicitacaoAluno : solicitacoesAluno) {
        // System.out.println("Código: " + solicitacaoAluno.getIdSolicitacao());
        // System.out.println("Nome: " + solicitacaoAluno.getNome());
        // System.out.println("Descrição: " + solicitacaoAluno.getDescricao());
        // System.out.println("Anexo: " + solicitacaoAluno.getAnexo());
        // System.out.println("Status: " + solicitacaoAluno.getStatus());

        // SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        // String dataFormatada =
        // sdf.format(solicitacaoAluno.getDataAbertura().getTime());
        // System.out.println("Data de abertura: " + dataFormatada);

        // System.out.println("Prazo: " + solicitacaoAluno.getPrazo());
        // System.out.println("Código do aluno: " +
        // solicitacaoAluno.getAluno().getRa());
        // }

        // ArrayList<Solicitacao> solicitacoesADM =
        // soliService.selectSolicitacoesADM(adm);
        //
        // for(Solicitacao solicitacaoADM : solicitacoesADM) {
        // System.out.println("Código: " + solicitacaoADM.getIdSolicitacao());
        // System.out.println("Nome: " + solicitacaoADM.getNome());
        // System.out.println("Descrição: " + solicitacaoADM.getDescricao());
        // System.out.println("Anexo: " + solicitacaoADM.getAnexo());
        // System.out.println("Status: " + solicitacaoADM.getStatus());
        //
        // SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        // String dataFormatada =
        // sdf.format(solicitacaoADM.getDataAbertura().getTime());
        // System.out.println("Data de abertura: " + dataFormatada);
        //
        // System.out.println("Prazo: " + solicitacaoADM.getPrazo());
        // System.out.println("Código do aluno: " + solicitacaoADM.getAluno().getRa() +
        // "\n");
        // }

        // ArrayList<Comentario> comentariosGeral =
        // comService.selectComentariosDados(soli2);
        //
        // for(Comentario comentarios : comentariosGeral) {
        // System.out.println("Codigo: " + comentarios.getIdComentario());
        // System.out.println("Texto: " + comentarios.getTexto());
        // System.out.println("Anexo: " + comentarios.getAnexo());
        //
        // SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // String dataFormatada = sdf.format(comentarios.getDataHora().getTime());
        // System.out.println("Data de abertura: " + dataFormatada);
        //
        // System.out.println("Codigo do aluno: " + comentarios.getAluno().getRa());
        // System.out.println("Email do adm: " +
        // comentarios.getAdministrador().getEmail());
        // System.out.println("Codigo da Solicitacao: " +
        // comentarios.getSolicitacao().getIdSolicitacao() + "\n");
        // }

        // Administrador adminis = admService.selectAdminGeral(adm);
        // System.out.println("Nome: " + adminis.getNome());
        // System.out.println("Sobrenome: " + adminis.getSobrenome());
        // System.out.println("CPF: " + adminis.getCpf());
        // System.out.println("Status: " + adminis.getStatus());
        // System.out.println("E-mail: " + adminis.getEmail());
        // System.out.println("Senha: " + adminis.getSenha());
        // System.out.println("Codigo setor: " + adminis.getSetor().getIdSetor());

        // ArrayList<Administrador> administradoresOrder =
        // admService.selectAdminOrder();
        //
        // for(Administrador adminis : administradoresOrder) {
        // System.out.println("Nome: " + adminis.getNome());
        // System.out.println("Sobrenome: " + adminis.getSobrenome());
        // System.out.println("CPF: " + adminis.getCpf());
        // System.out.println("Status: " + adminis.getStatus());
        // System.out.println("E-mail: " + adminis.getEmail());
        // System.out.println("Senha: " + adminis.getSenha());
        // System.out.println("Codigo setor: " + adminis.getSetor().getIdSetor() +
        // "\n");
        //
        // }

        // //Todos os dados do Aluno
        // Aluno aluninho = alnService.selectAluno(aln1);
        // System.out.println("Nome: " + aluninho.getNome());
        // System.out.println("Sobrenome: " + aluninho.getSobrenome());
        // System.out.println("RA: " + aluninho.getRa());
        // System.out.println("Status: " + aluninho.getStatus());
        // System.out.println("CPF: " + aluninho.getCpf());

        // SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        // String dataFormatada = sdf.format(aluninho.getData_nascimento().getTime());
        
        // System.out.println("Data de nascimento: " + dataFormatada);

        // System.out.println("Curso: " + aluninho.getCurso());
        // System.out.println("Turno: " + aluninho.getTurno());
        // System.out.println("Unidade: " + aluninho.getUnidade());
        // System.out.println("Semestre: " + aluninho.getSemestre());
        // System.out.println("Email: " + aluninho.getEmail());
        // System.out.println("Senha: " + aluninho.getSenha());
        // System.out.println("FK ADM: " + aluninho.getAdm().getEmail());


        // ArrayList<Aluno> alunos = alnService.listarAlunos();

        // for (Aluno aluno : alunos) {
        //     System.out.println("Nome: " + aluno.getNome());
        //     System.out.println("Sobrenome: " + aluno.getSobrenome());
        //     System.out.println("RA: " + aluno.getRa());
        //     System.out.println("Status: " + aluno.getStatus());
        //     System.out.println("CPF: " + aluno.getCpf());

        //     SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        //     String dataFormatada = sdf.format(aluno.getData_nascimento().getTime());
            
        //     System.out.println("Data de nascimento: " + dataFormatada);

        //     System.out.println("Curso: " + aluno.getCurso());
        //     System.out.println("Turno: " + aluno.getTurno());
        //     System.out.println("Unidade: " + aluno.getUnidade());
        //     System.out.println("Semestre: " + aluno.getSemestre());
        //     System.out.println("Email: " + aluno.getEmail());
        //     System.out.println("Senha: " + aluno.getSenha());
        //     System.out.println("FK ADM: " + aluno.getAdm().getEmail() + "\n");
        // }

        // teste de setor read
        // ArrayList<Setor> setores = setService.listarSetores();
        // for (Setor setor1 : setores) {
        //     System.out.println(setor1.getIdSetor());
        //     System.out.println(setor1.getNome());
        // }

        // Validation v = admService.createValidation(adm);
        
        //Se já existir um ADM com aquele email no banco
        //Se retornar true avisa quem ta cadastrando
        //Se retornar false, pode continuar
        // if(v.getStatus()) {
        //     //Mensagem de erro para o usuário
        //     System.out.println(v.getText());
        // } else {
        //     //Seguindo o baile pq ta tudo certo
        //     admService.create(adm);
        // }

        //Antes de dar o update de primeiro acesso do ADM verificar se o CPF já 
        //não existe no banco e se o email confere com o email de algum ADM INATIVO no banco
        //se retornar true aquele cara já existe ou nao tem acesso para se cadastrar
        //se retornar false ta tudo certo
        // Validation v2 = admService.updateInicialValidation(adm);

        // if(v2.getStatus()) {
        //     //Mensagem de erro para o usuário
        //     System.out.println(v2.getText());
        // } else {
        //     //Seguindo o baile pq ta tudo certo
        //     admService.updateInicial(adm);
        // }

        //Login e senha do ADM
        //Se o ADM estiver inativo, com senha ou email errados o acesso é negado e retorna true
        //Se os ADM estiver ativo e com dados certos retorna false
        // Validation v3 = admService.loginValidation(adm);

        // if(v3.getStatus()) {
        //     //Mensagem de erro para o usuário
        //     System.out.println(v3.getText());
        // } else {
        //     //Seguindo o baile pq ta tudo certo
        //     System.out.println("Deu certo, bora pra próxima página");
        // }

        Validation a = alnService.createValidation(aln1);

        //Antes de cadastrar um aluno verificar se o email 
        //ou o CPF já existem em algum registro no banco
        //se der erro volta true
        //se nao segue o baile
        if(a.getStatus()) {
            //Mensagem de erro para o usuário
            System.out.println(a.getText());
        } else {
            System.out.println("Segue o baile DEV");
            alnService.create(aln1);
        }
    }
}