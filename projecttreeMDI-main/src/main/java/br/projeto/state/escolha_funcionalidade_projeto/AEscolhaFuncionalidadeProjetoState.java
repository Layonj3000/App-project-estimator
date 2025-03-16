/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_funcionalidade_projeto;

import br.projeto.presenter.EscolhaFuncionalidadesProjetoPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author David Potentini
 */
public class AEscolhaFuncionalidadeProjetoState {
    protected EscolhaFuncionalidadesProjetoPresenter escolhaFuncionalidadeProjetoPresenter;

    public AEscolhaFuncionalidadeProjetoState(EscolhaFuncionalidadesProjetoPresenter escolhaFuncionalidadeProjetoPresenter) {
        this.escolhaFuncionalidadeProjetoPresenter = escolhaFuncionalidadeProjetoPresenter;
    }
    
    public void confirmar(){
        JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL REALIZAR A OPERAÇÃO NESSE ESTADO");//IDENTIFICAR O NOME DO ESTADO
        throw new RuntimeException();
    }
    
}
