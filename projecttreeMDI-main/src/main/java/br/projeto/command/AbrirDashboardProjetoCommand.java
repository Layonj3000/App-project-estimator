package br.projeto.command;

import br.projeto.presenter.DashBoardProjetoPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.view.DashBoardProjetoView;

import javax.swing.*;

public class AbrirDashboardProjetoCommand implements Command {
    private final JDesktopPane desktop;
    private final ProjetoRepositoryMock repository;

    public AbrirDashboardProjetoCommand(JDesktopPane desktop, ProjetoRepositoryMock repository) {
        this.desktop = desktop;
        this.repository = repository;
    }

    @Override
    public void execute() {
        String tituloJanela = "Dashboard de Projetos";
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(tituloJanela)) {
            windowManager.bringToFront(tituloJanela);
        } else {
            DashBoardProjetoView dashboardView = new DashBoardProjetoView();
            new DashBoardProjetoPresenter(dashboardView, repository);
            dashboardView.setTitle(tituloJanela);
            desktop.add(dashboardView);
            dashboardView.setVisible(true);
            try {
                dashboardView.setMaximum(true);
            } catch (Exception ignored) {
            }
        }
    }
}
