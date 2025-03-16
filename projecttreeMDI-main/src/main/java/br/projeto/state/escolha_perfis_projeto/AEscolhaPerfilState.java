/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_perfis_projeto;

import br.projeto.command.MostrarMensagemCommand;
import br.projeto.presenter.EscolhaPerfilPresenter;

/**
 *
 * @author David Potentini
 */
public class AEscolhaPerfilState {
    protected EscolhaPerfilPresenter escolhaPerfilPresenter;

    public AEscolhaPerfilState(EscolhaPerfilPresenter escolhaPerfilPresenter) {
        this.escolhaPerfilPresenter = escolhaPerfilPresenter;
    }
    
    public void confirmar(){
        new MostrarMensagemCommand("NÃO É POSSIVEL REALIZAR A OPERAÇÃO NESSE ESTADO").execute();
        throw new RuntimeException();
    }
    
}
