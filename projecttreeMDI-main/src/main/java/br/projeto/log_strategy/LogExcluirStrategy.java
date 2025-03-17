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
public class LogExcluirStrategy extends ALogStrategy{

    public LogExcluirStrategy(UsuarioModel usuarioModel) {
        super(usuarioModel);
    }
    
    @Override
    public void gerarLOG() {
        LogRegister logRegister = new LogRegister("Exclusão de Projeto", usuarioModel.getNome(),
        usuarioModel.getEmail(), true, "Sucesso");

        logService.setLogRegister(logRegister);
        logService.notificar();    
    }
    
}
