/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import br.projeto.log_strategy.ALogStrategy;
import br.projeto.log_strategy.ErroLogCompartilharStrategy;
import br.projeto.log_strategy.ErroLogExportarStrategy;
import br.projeto.log_strategy.LogCompartilharStrategy;
import br.projeto.log_strategy.LogCriarStrategy;
import br.projeto.log_strategy.LogExcluirStrategy;
import br.projeto.log_strategy.LogExportarStrategy;
import br.projeto.model.UsuarioModel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David Potentini
 */
public class LogStrategyService {
    private Map<String, ALogStrategy> mapLogStrategy;
    private UsuarioModel usuarioModel;
    
    public LogStrategyService(UsuarioModel usuarioModel) {
        mapLogStrategy = new HashMap<>();
        
        mapLogStrategy.put("Criação", new LogCriarStrategy(usuarioModel));
        mapLogStrategy.put("Exclusão", new LogExcluirStrategy(usuarioModel));
        mapLogStrategy.put("Compartilhamento", new LogCompartilharStrategy(usuarioModel));
        mapLogStrategy.put("Exportação", new LogExportarStrategy(usuarioModel));
        mapLogStrategy.put("Erro Compartilhamento", new ErroLogCompartilharStrategy(usuarioModel));
        mapLogStrategy.put("Erro Exportação", new ErroLogExportarStrategy(usuarioModel));
    }
    
    
    public void gerarLOG(String tipoLog){
        try{
            ALogStrategy logStrategy = mapLogStrategy.get(tipoLog);
            logStrategy.gerarLOG();
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    
    
    
}
