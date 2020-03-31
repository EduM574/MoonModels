package service;

import dao.Conexao;
import dao.AdministradorDAO;
import model.Administrador;

public class AdministradorService {
    AdministradorDAO admDAO = new AdministradorDAO(Conexao.conectar());

    public void create(Administrador adm) {
        adm.setStatus("INATIVO");
		admDAO.createAdminitrador(adm);
    }

    public void updateInicialAdministrador(Administrador adm) {
        adm.setStatus("ATIVO");
        admDAO.updateInicialAdministrador(adm);
    }
    
    public void updateTotal(Administrador adm) {
        admDAO.updateTotalAdministrador(adm);
    }
}