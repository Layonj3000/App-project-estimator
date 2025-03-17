/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.projeto.log_strategy;

import br.projeto.model.UsuarioModel;
import br.projeto.observer.FileLogger;
import br.projeto.observer.LogNotifier;
import br.projeto.service.LogService;

/**
 *
 * @author David Potentini
 */
public abstract class ALogStrategy {
    protected FileLogger fileLogger = new FileLogger();
    protected LogNotifier logNotifier = new LogNotifier();
    protected UsuarioModel usuarioModel;
    protected LogService logService;
    
    public ALogStrategy(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
        
        logService = new LogService(usuarioModel.getFormatoLOG());
        logService.getLogNotifier().add(fileLogger);
    }
    
    
    public abstract void gerarLOG();
}
