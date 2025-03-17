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
public class LogCriarStrategy extends ALogStrategy{

    public LogCriarStrategy(UsuarioModel usuarioModel) {
        super(usuarioModel);
    }


    @Override
    public void gerarLOG() {
                LogRegister logRegister = new LogRegister("Criação de Projeto", usuarioModel.getNome(),
                usuarioModel.getEmail(), true, "Sucesso");

                logService.setLogRegister(logRegister);
                logService.notificar();    
    }
    
}
