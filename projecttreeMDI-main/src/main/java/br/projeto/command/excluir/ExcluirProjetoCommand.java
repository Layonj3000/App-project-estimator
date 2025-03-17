package br.projeto.command.excluir;

import br.projeto.command.Command;
import br.projeto.command.MostrarMensagemCommand;
import br.projeto.model.UsuarioModel;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.service.LogStrategyService;

import javax.swing.*;

public class ExcluirProjetoCommand implements Command{
    private ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private Integer projetoId;
    private String projetoNome;
    private final UsuarioModel usuarioModel;
    
    private final LogStrategyService logStrategyService;
    
    public ExcluirProjetoCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository, UsuarioModel usuarioModel){
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.usuarioModel = usuarioModel;
        
        logStrategyService = new LogStrategyService(usuarioModel); 
    }
    
    public ExcluirProjetoCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository, Integer projetoId,UsuarioModel usuarioModel){
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.projetoId = projetoId;
        this.usuarioModel = usuarioModel;
        
        logStrategyService = new LogStrategyService(usuarioModel); 
    }

    
    public void setProjetoNome(String projetoNome) {
        this.projetoNome = projetoNome;
    }

    public void setProjetoId(Integer projetoId) {
        this.projetoId = projetoId;
    }
    
    
    //OBS: AS TABELAS INTERMEDIARIA E DE FUNCIONALIDADES EXTRAS EXCLUEM AUTOMATICAMENTE APOS EXCLUIR O PERFIL
    @Override
    public void execute(){
         if (projetoId == null) {
            throw new IllegalArgumentException("Id do projeto não definido.");
        }

        int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente excluir o projeto \"" + projetoDeEstimativaRepository.findById(projetoId).getNomeProjetoDeEstimativa() + "\"?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            projetoNome = projetoDeEstimativaRepository.findById(projetoId).getNomeProjetoDeEstimativa();
            boolean removido = projetoDeEstimativaRepository.deleteById(projetoId);
            if (removido) {
                new MostrarMensagemCommand("Projeto \"" + projetoNome + "\" removido com sucesso!").execute();
            } else {
                new MostrarMensagemCommand("Erro ao remover o projeto \"" + projetoDeEstimativaRepository.findById(projetoId).getNomeProjetoDeEstimativa() + "\".").execute();
            }
        }
            
        logRegister();
    }
    
    private void logRegister(){
        logStrategyService.gerarLOG("Exclusão");
    }
}
