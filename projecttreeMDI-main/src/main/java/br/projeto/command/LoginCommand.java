/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.presenter.LoginUsuarioPresenter;
import br.projeto.presenter.PrincipalPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.repository.UsuarioRepository;
import br.projeto.service.InstanciaRepositoryService;
import br.projeto.view.TelaLogin;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author layon
 */
public class LoginCommand implements Command {
    private LoginUsuarioPresenter loginUsuarioPresenter;
    private UsuarioRepository usuarioRepository = InstanciaRepositoryService.getInstancia().getUsuarioRepository();
    
    public LoginCommand(LoginUsuarioPresenter loginUsuarioPresenter) {
        this.loginUsuarioPresenter = loginUsuarioPresenter;
    }

    @Override
    public void execute() {
        if (usuarioRepository.findByEmailandPassword(loginUsuarioPresenter.getEmail(), loginUsuarioPresenter.getSenha()) != null) {
            UsuarioModel usuarioModel = usuarioRepository.findByEmailandPassword(loginUsuarioPresenter.getEmail(), loginUsuarioPresenter.getSenha());
            loginUsuarioPresenter.getView().dispose();
            SwingUtilities.invokeLater(() -> {
                PrincipalPresenter presenter = new PrincipalPresenter(usuarioModel);
                WindowManager.getInstance().initialize(presenter);
            });
        } else {
            JOptionPane.showMessageDialog(null, "Email ou senha inválidos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}
