package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.presenter.LoginUsuarioPresenter;
import br.projeto.presenter.PrincipalPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.UsuarioRepository;
import br.projeto.service.InstanciaRepositoryService;
import javax.swing.SwingUtilities;

public class LoginCommand implements Command {
    private final LoginUsuarioPresenter loginUsuarioPresenter;
    private final UsuarioRepository usuarioRepository = InstanciaRepositoryService.getInstancia().getUsuarioRepository();

    public LoginCommand(LoginUsuarioPresenter loginUsuarioPresenter) {
        this.loginUsuarioPresenter = loginUsuarioPresenter;
    }

    @Override
    public void execute() {
        UsuarioModel usuarioModel = usuarioRepository.findByEmailandPassword(
            loginUsuarioPresenter.getEmail(),
            loginUsuarioPresenter.getSenha()
        );

        if (usuarioModel == null) {
            throw new IllegalArgumentException("Email ou senha inválidos!");
        }

        loginUsuarioPresenter.getView().dispose();
        SwingUtilities.invokeLater(() -> {
            PrincipalPresenter presenter = new PrincipalPresenter(usuarioModel);
            WindowManager.getInstance().initialize(presenter);
        });
    }
}