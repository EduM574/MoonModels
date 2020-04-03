package service;

import dao.Conexao;
import dao.GerenciaAdmSolicitacaoDAO;
import model.GerenciaAdmSolicitacao;

public class GerenciaAdmSolicitacaoService {
    GerenciaAdmSolicitacaoDAO gerenciaDAO = new GerenciaAdmSolicitacaoDAO(Conexao.conectar());

    public void create(GerenciaAdmSolicitacao gerencia) {
        gerenciaDAO.createRegistro(gerencia);
    }
}