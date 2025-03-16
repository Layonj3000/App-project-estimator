/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.presenter.ExportarPresenter;

/**
 *
 * @author layon
 */
public class IniciarTelaExportarCommand implements Command{

    public IniciarTelaExportarCommand() {
    }

    @Override
    public void execute() {
        new ExportarPresenter();
    }
    
}
