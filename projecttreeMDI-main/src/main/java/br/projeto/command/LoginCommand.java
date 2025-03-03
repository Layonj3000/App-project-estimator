/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.repository.UsuarioRepository;
import br.projeto.repository_factory.RepositoryFactory;

/**
 *
 * @author layon
 */
public class LoginCommand implements Command{
    private String email;
    private String senha;

    public LoginCommand(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    @Override
    public void execute() {
        RepositoryFactory factory1 = RepositoryFactory.escolherClasseFabricada(UsuarioRepository.class);
        UsuarioRepository usuarioRepository = factory1.createRepository();
   
        if (usuarioRepository.verificarLogin(email, senha) == true) {
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Email ou senha inválidos!");
        }
    }
}
