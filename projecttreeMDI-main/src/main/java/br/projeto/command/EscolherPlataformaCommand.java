/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.presenter.ProjetoDeEstimativaPresenter;

/**
 *
 * @author USER
 */
public class EscolherPlataformaCommand implements ProjetoCommand{
    private final ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter;

    public EscolherPlataformaCommand(ProjetoDeEstimativaPresenter projetoDeEstimativaPresenter) {
        this.projetoDeEstimativaPresenter = projetoDeEstimativaPresenter;
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
