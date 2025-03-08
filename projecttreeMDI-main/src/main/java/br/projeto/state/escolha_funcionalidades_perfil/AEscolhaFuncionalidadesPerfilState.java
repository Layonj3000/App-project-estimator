/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state.escolha_funcionalidades_perfil;

import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public abstract class AEscolhaFuncionalidadesPerfilState {
    protected EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter;

    public AEscolhaFuncionalidadesPerfilState(EscolhaFuncionalidadesPerfilPresenter escolhaFuncionalidadesPerfilPresenter) {
        this.escolhaFuncionalidadesPerfilPresenter = escolhaFuncionalidadesPerfilPresenter;
        //CRIAR UM METODO REMOVE LISTENERS E ADICIONAR A CHAMADA AQUI
    }

    public void salvar(){
        JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL REALIZAR A OPERAÇÃO NESSE ESTADO");//IDENTIFICAR O NOME DO ESTADO
        throw new RuntimeException();
    }
    
    public void voltar(){
        JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL REALIZAR A OPERAÇÃO NESSE ESTADO");//IDENTIFICAR O NOME DO ESTADO
        throw new RuntimeException();    }
    
    
}
