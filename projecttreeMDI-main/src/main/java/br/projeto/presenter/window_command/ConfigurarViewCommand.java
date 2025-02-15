package br.projeto.presenter.window_command;


import br.projeto.command.MostrarMensagemProjetoCommand;
import br.projeto.command.ProjetoCommand;
import br.projeto.presenter.PrincipalPresenter;
import br.projeto.service.CriarBarraBotaoService;

import javax.swing.*;

public class ConfigurarViewCommand implements WindowCommand {
    private final PrincipalPresenter presenter;

    public ConfigurarViewCommand(PrincipalPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.configurarArvore();
        JToolBar barraDeBotoes = new CriarBarraBotaoService(presenter.getComandos()).criarBarraDeBotoes();
        presenter.getView().setMainComponents(barraDeBotoes);

        SwingUtilities.invokeLater(() -> {
            ProjetoCommand comandoPrincipal = presenter.getComandos().get("Principal");
            if (comandoPrincipal != null) {
                comandoPrincipal.execute();
            } else {
                new MostrarMensagemProjetoCommand("Comando 'Principal' não encontrado.").execute();
            }
        });
    }
}
