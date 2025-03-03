/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.repository.UsuarioRepository;
import br.projeto.repository_factory.RepositoryFactory;



/**
 *
 * @author layon
 */
public class RegistroCommand implements Command{
    private String nome;
    private String email;
    private String senha;

    public RegistroCommand(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public void execute() {
        RepositoryFactory factory1 = RepositoryFactory.escolherClasseFabricada(UsuarioRepository.class);
        UsuarioRepository usuarioRepository = factory1.createRepository();
        UsuarioModel novousuario = new UsuarioModel(5,nome, senha, email);
        usuarioRepository.insert(novousuario);
        System.out.println(usuarioRepository.findAll());
    }
}
