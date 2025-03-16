/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_funcionalidades_perfil;

import br.projeto.command.MostrarMensagemCommand;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;

/**
 *
 * @author David Potentini
 */
public abstract class AEscolhaFuncionalidadesPerfilState {
    protected EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter;

    public AEscolhaFuncionalidadesPerfilState(EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter) {
        this.escolhaFuncionalidadesPerfilPresenter = escolhaFuncionalidadesPerfilPresenter;
    }

    public void salvar(){
        new MostrarMensagemCommand("NÃO É POSSIVEL REALIZAR A OPERAÇÃO NESSE ESTADO").execute();
        throw new RuntimeException();
    }
    
    
    
}
