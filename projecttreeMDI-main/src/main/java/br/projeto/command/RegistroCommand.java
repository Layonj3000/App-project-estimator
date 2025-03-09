/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.presenter.RegistroUsuarioPresenter;
import br.projeto.registro_proxy.RegistroProxy;
import br.projeto.repository.UsuarioRepository;
import br.projeto.repository_factory.RepositoryFactory;
import javax.swing.JOptionPane;



/**
 *
 * @author layon
 */
public class RegistroCommand implements Command {
    
    private RegistroUsuarioPresenter registroUsuarioPresenter;

    public RegistroCommand(RegistroUsuarioPresenter registroUsuarioPresenter) {
        this.registroUsuarioPresenter = registroUsuarioPresenter;
    }

    @Override
    public void execute() {
        
        RepositoryFactory factory1 = RepositoryFactory.escolherClasseFabricada(UsuarioRepository.class);
        UsuarioRepository usuarioRepository = factory1.createRepository();

        UsuarioModel novoUsuario = new UsuarioModel(null, registroUsuarioPresenter.getNomeUsuario(), registroUsuarioPresenter.getSenha(), registroUsuarioPresenter.getEmail(), "CSV");
        usuarioRepository.insert(novoUsuario);

        JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!");
        
    }
}