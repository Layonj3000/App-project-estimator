/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.state.TelaState;

/**
 *
 * @author layon
 */
public class TelaPresenter {
    private TelaState telaAtual;

    public TelaPresenter(TelaState estadoInicial) {
        this.telaAtual = estadoInicial;
        telaAtual.exibirTela();
    }

    public void mudarEstado(TelaState novoEstado) {    
        this.telaAtual = novoEstado;
        telaAtual.exibirTela(); // Exibe a nova tela
    }            

}
