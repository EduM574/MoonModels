package service;

import dao.ConnectionFactory;
import dao.GerenciaAdmSolicitacaoDAO;
import model.GerenciaAdmSolicitacao;

public class GerenciaAdmSolicitacaoService {
    GerenciaAdmSolicitacaoDAO gerenciaDAO = new GerenciaAdmSolicitacaoDAO(ConnectionFactory.conectar());

    public void create(GerenciaAdmSolicitacao gerencia) {
        gerenciaDAO.createRegistro(gerencia);
    }
}