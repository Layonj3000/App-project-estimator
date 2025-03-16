package br.projeto.presenter.window_command;

import br.projeto.model.ProjetoDeEstimativaModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FecharJanelasProjetoRelacionadasCommand implements WindowCommand {
    private final JDesktopPane desktop;
    private final List<ProjetoDeEstimativaModel> listaProjetoDeEstimativaModel;

    public FecharJanelasProjetoRelacionadasCommand(JDesktopPane desktop, List<ProjetoDeEstimativaModel> listaProjetoDeEstimativaModel) {
        this.desktop = desktop;
        this.listaProjetoDeEstimativaModel = listaProjetoDeEstimativaModel;
    }

    @Override
    public void execute() {
        List<String> nomesProjetos = new ArrayList<>();
        for (ProjetoDeEstimativaModel projetoDeEstimativaModel:listaProjetoDeEstimativaModel){
            nomesProjetos.add(projetoDeEstimativaModel.getNomeProjetoDeEstimativa());
        }

        JInternalFrame[] quadrosInternos = desktop.getAllFrames();
        for (JInternalFrame quadroInterno : quadrosInternos) {
            if (quadroInterno.getTitle().startsWith("Detalhes do Projeto: ")) {
                String nomeProjeto = quadroInterno.getTitle().replace("Detalhes do Projeto: ", "");
                if (!nomesProjetos.contains(nomeProjeto)) {
                    quadroInterno.dispose();
                }
            }
        }
    }
}
