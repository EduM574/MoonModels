package main;

import dao.Conexao;
import dao.AdministradorDAO;
import model.Administrador;
import model.Setor;

public class Main {
    public static void main(String args[]) {
        AdministradorDAO admDAO = new AdministradorDAO(Conexao.conectar());

        Setor setor = new Setor(1, "", "", "", null);
        Administrador adm = new Administrador("", "", "INATIVO", 0, "fulano@usjt.br", "", setor, null, null, null);

        admDAO.createAdminitrador(adm);
    }
}