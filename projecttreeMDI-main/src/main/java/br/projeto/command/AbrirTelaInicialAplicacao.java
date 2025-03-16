package br.projeto.command;

import br.projeto.presenter.helpers.WindowManager;
import br.projeto.view.TelaInicialAplicacaoView;

import javax.swing.*;

public class AbrirTelaInicialAplicacao implements Command {
    private final JDesktopPane desktop;

    public AbrirTelaInicialAplicacao(JDesktopPane desktop) {
        this.desktop = desktop;
    }

    @Override
    public void execute() {
        String tituloJanela = "Tela Inicial da Aplicação";
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(tituloJanela)) {
            windowManager.bringToFront(tituloJanela);
        } else {
            TelaInicialAplicacaoView telaInicialAplicacaoView = new TelaInicialAplicacaoView();
            telaInicialAplicacaoView.setTitle(tituloJanela);
            desktop.add(telaInicialAplicacaoView);
            telaInicialAplicacaoView.setVisible(true);
            
            try {
                telaInicialAplicacaoView.setMaximum(true);
                /*dashboardView.setMaximum(true);*/
            } catch (Exception ignored) {
            }
        }
    }
}
