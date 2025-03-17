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
public class ErroLogCompartilharStrategy extends ALogStrategy{

    public ErroLogCompartilharStrategy(UsuarioModel usuarioModel) {
        super(usuarioModel);
    }

    @Override
    public void gerarLOG() {
        LogRegister logRegister = new LogRegister("Erro Durante Compartilhamento de Projeto", usuarioModel.getNome(),usuarioModel.getEmail(), true, "Sucesso");
        logService.setLogRegister(logRegister);
        logService.notificar();    
    }
    
}
