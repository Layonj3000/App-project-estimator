/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_funcionalidade_projeto;

import br.projeto.command.MostrarMensagemCommand;
import br.projeto.presenter.EscolhaFuncionalidadesProjetoPresenter;

/**
 *
 * @author David Potentini
 */
public class AEscolhaFuncionalidadeProjetoState {
    protected EscolhaFuncionalidadesProjetoPresenter escolhaFuncionalidadesProjetoPresenter;

    public AEscolhaFuncionalidadeProjetoState(EscolhaFuncionalidadesProjetoPresenter escolhaFuncionalidadeProjetoPresenter) {
        this.escolhaFuncionalidadesProjetoPresenter = escolhaFuncionalidadeProjetoPresenter;
    }
    
    public void confirmar(){
        new MostrarMensagemCommand("NÃO É POSSIVEL REALIZAR A OPERAÇÃO NESSE ESTADO").execute();
        throw new RuntimeException();
    }
    
}
