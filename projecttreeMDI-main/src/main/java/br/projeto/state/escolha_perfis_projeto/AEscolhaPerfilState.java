/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_perfis_projeto;

import br.projeto.presenter.EscolhaPerfilPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class AEscolhaPerfilState {
    protected EscolhaPerfilPresenter escolhaPerfilPresenter;

    public AEscolhaPerfilState(EscolhaPerfilPresenter escolhaPerfilPresenter) {
        this.escolhaPerfilPresenter = escolhaPerfilPresenter;
    }
    
    public void confirmar(){
        JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL REALIZAR A OPERAÇÃO NESSE ESTADO");
        throw new RuntimeException();
    }
    
    public void voltar(){
        JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL REALIZAR A OPERAÇÃO NESSE ESTADO");
        throw new RuntimeException();
    }
}
