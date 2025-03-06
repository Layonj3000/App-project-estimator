/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.presenter.PrincipalPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.service.LoginService;
import br.projeto.view.TelaLogin;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author layon
 */
public class LoginCommand implements Command {

    private TelaLogin telaLogin;
    private String email;
    private String senha;
    private LoginService loginService;

    public LoginCommand(TelaLogin telaLogin, String email, String senha) {
        this.telaLogin = telaLogin;
        this.email = email;
        this.senha = senha;
        this.loginService = new LoginService(); 
    }

    @Override
    public void execute() {
        if (loginService.verificarLogin(email, senha)) {
            UsuarioModel usuarioModel = loginService.obterUsuario(email, senha);
            telaLogin.dispose();
            SwingUtilities.invokeLater(() -> {
                PrincipalPresenter presenter = new PrincipalPresenter(
                        new ProjetoRepositoryMock(),
                        loginService.getProjetoDeEstimativaRepository(),
                        loginService.getPerfilProjetoDeEstimativaRepository(),
                        loginService.getProjetoFuncionalidadesPersonalizadasRepository(),
                        loginService.getPerfilFuncionalidadesPersonalizadasRepository(),
                        loginService.getPerfilPerfilProjetoIntermediariaRepository(),
                        usuarioModel
                );
                WindowManager.getInstance().initialize(presenter);
            });
        } else {
            JOptionPane.showMessageDialog(null, "Email ou senha inválidos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}
