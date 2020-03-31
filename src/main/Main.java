package main;

import model.Administrador;
import model.Setor;
import service.AdministradorService;

public class Main {
    public static void main(String args[]) {
        AdministradorService admService = new AdministradorService();

        // Insert de novos adm (email, setor, status) o status começa com INATIVO
        Setor setor = new Setor(1, "", "", "", null);
        Administrador adm = new Administrador("", "", "", "", "fulano2@usjt.br", "", setor, null, null, null);
        admService.create(adm);

        //Update em qualquer adm (todos os parâmetros exceto email)
        Setor setor2 = new Setor(2, "", "", "", null);
        Administrador adm2 = new Administrador("João", "Fulano", "ATIVO", "48997195824", "fulano2@usjt.br", "1234", setor2, null, null, null);
        admService.updateTotal(adm2);
    }
}