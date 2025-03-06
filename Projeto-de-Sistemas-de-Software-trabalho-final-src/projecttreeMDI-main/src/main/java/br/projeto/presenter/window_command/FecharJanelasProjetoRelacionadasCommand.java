package br.projeto.presenter.window_command;

import br.projeto.model.Projeto;
import br.projeto.model.ProjetoDeEstimativaModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import br.projeto.command.Command;

public class FecharJanelasProjetoRelacionadasCommand implements WindowCommand {
    private final JDesktopPane desktop;
    // final List<Projeto> listaProjetos;//ANTIGO
    private final List<ProjetoDeEstimativaModel> listaProjetoDeEstimativaModel;//NOVO

    public FecharJanelasProjetoRelacionadasCommand(JDesktopPane desktop, List<ProjetoDeEstimativaModel> listaProjetoDeEstimativaModel /*List<Projeto> listaProjetos*//*ANTIGO*/) {
        this.desktop = desktop;
        //this.listaProjetos = listaProjetos;//ANTIGO
        this.listaProjetoDeEstimativaModel = listaProjetoDeEstimativaModel;
    }

    @Override
    public void execute() {
        List<String> nomesProjetos = new ArrayList<>();
        //for (Projeto projeto : listaProjetos) {//ANTIGO
        for (ProjetoDeEstimativaModel projetoDeEstimativaModel:listaProjetoDeEstimativaModel){
            //nomesProjetos.add(projeto.getNome());//ANTIGO
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
