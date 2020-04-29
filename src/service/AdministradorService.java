package service;

import dao.ConnectionFactory;
import dao.AdministradorDAO;
import model.Administrador;
import model.Validation;

import java.util.ArrayList;

public class AdministradorService {
    AdministradorDAO admDAO = new AdministradorDAO(ConnectionFactory.conectar());
    Validation admV = new Validation();

    public void create(Administrador adm) {
        adm.setStatus("ATIVO");
        adm.setSenha(adm.getCpf());
		admDAO.createAdminitrador(adm);
    }
    
    public void updateTotal(Administrador adm) {
        admDAO.updateTotalAdministrador(adm);
    }

    public void updateSenha(Administrador adm) {
        admDAO.updateSenhaAdministrador(adm);
    }
    
    public Administrador selectAdminGeral(Administrador adm) {
    	return admDAO.adminGeral(adm);
    }
    
    public ArrayList<Administrador> selectAdminOrder(){
    	return admDAO.adminOrder();
    }

    public Validation createValidation(Administrador adm) {        
        return admDAO.createValidation(adm);
    }

    public Validation loginValidation(Administrador adm) {
        return admDAO.loginValidation(adm);
    }
}