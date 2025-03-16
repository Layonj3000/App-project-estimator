/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter.window_command;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author David Potentini
 */
public class FecharJanelasPerfilRelacionadasCommand implements WindowCommand{
    private final JDesktopPane desktop;
    // final List<Projeto> listaProjetos;//ANTIGO
    private final List<PerfilProjetoDeEstimativaModel> listaPerfilProjetoDeEstimativaModel;//NOVO

    public FecharJanelasPerfilRelacionadasCommand(JDesktopPane desktop, List<PerfilProjetoDeEstimativaModel> listaPerfilProjetoDeEstimativaModel /*List<Projeto> listaProjetos*//*ANTIGO*/) {
        this.desktop = desktop;
        //this.listaProjetos = listaProjetos;//ANTIGO
        this.listaPerfilProjetoDeEstimativaModel = listaPerfilProjetoDeEstimativaModel;
    }

    @Override
    public void execute() {
        List<String> nomesPerfis = new ArrayList<>();
        //for (Projeto projeto : listaProjetos) {//ANTIGO
        for (PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel:listaPerfilProjetoDeEstimativaModel){
            //nomesProjetos.add(projeto.getNome());//ANTIGO
            nomesPerfis.add(perfilProjetoDeEstimativaModel.getNomePerfil());
        }

        JInternalFrame[] quadrosInternos = desktop.getAllFrames();
        for (JInternalFrame quadroInterno : quadrosInternos) {
            if (quadroInterno.getTitle().startsWith("Detalhes do Perfil: ")) {
                String nomePerfil = quadroInterno.getTitle().replace("Detalhes do Perfil: ", "");
                if (!nomesPerfis.contains(nomePerfil)) {
                    quadroInterno.dispose();
                }
            }
        }
    }
    
}
