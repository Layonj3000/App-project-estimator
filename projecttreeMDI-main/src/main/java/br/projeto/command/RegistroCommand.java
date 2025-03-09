/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.presenter.TelaPresenter;
import br.projeto.proxy.ProxyRegistro;
import br.projeto.repository.UsuarioRepository;
import br.projeto.repository_factory.RepositoryFactory;
import br.projeto.state.LoginState;
import br.projeto.view.TelaRegistro;
import javax.swing.JOptionPane;



/**
 *
 * @author layon
 */
public class RegistroCommand implements Command {
    private String nome;
    private String email;
    private String senha;
    private TelaRegistro telaRegistro;
    private ProxyRegistro proxy;

    public RegistroCommand(TelaRegistro telaRegistro, String nome, String email, String senha) {
        this.telaRegistro = telaRegistro;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.proxy = new ProxyRegistro();
    }

    @Override
    public void execute() {
        if (proxy.validarCampos(nome, email, senha)) {
            RepositoryFactory factory1 = RepositoryFactory.escolherClasseFabricada(UsuarioRepository.class);
            UsuarioRepository usuarioRepository = factory1.createRepository();

            UsuarioModel novoUsuario = new UsuarioModel(5, nome, senha, email, "CSV");
            usuarioRepository.insert(novoUsuario);

            JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!");
            telaRegistro.dispose();
            new TelaPresenter(new LoginState());
        }
    }
}