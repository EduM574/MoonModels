package main;

import model.Administrador;
import model.Setor;
import service.AdministradorService;

public class Main {
    public static void main(String args[]) {

        //Insert de novos adm (email, setor, status) o status começa com INATIVO
        Setor setor = new Setor(1, "", "", "", null);
        Administrador adm = new Administrador("", "", "", 0, "fulano2@usjt.br", "", setor, null, null, null);
        AdministradorService admService = new AdministradorService();
        admService.create(adm);
    }
}