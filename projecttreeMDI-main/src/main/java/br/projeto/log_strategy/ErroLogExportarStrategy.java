/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.log_strategy;

import br.projeto.model.UsuarioModel;
import com.log.model.LogRegister;

/**
 *
 * @author David Potentini
 */
public class ErroLogExportarStrategy extends ALogStrategy{

    public ErroLogExportarStrategy(UsuarioModel usuarioModel) {
        super(usuarioModel);
    }

    @Override
    public void gerarLOG() {
        LogRegister logRegister = new LogRegister("Exportar Projeto", usuarioModel.getNome(),usuarioModel.getEmail(), false, "Ocorreu um erro inesperado. Tente novamente.");
        logService.setLogRegister(logRegister);
        logService.notificar();   
    }
    
}
