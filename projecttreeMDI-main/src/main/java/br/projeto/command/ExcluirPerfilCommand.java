/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class ExcluirPerfilCommand implements Command{
    private PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private Integer perfilId;
    private String perfilNome;
    
    public ExcluirPerfilCommand(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository){
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
    }
    
    public ExcluirPerfilCommand(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, Integer perfilId){
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.perfilId = perfilId;
    }

    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }

    public void setPerfilNome(String perfilNome) {
        this.perfilNome = perfilNome;
    }
    
    //OBS: AS TABELAS INTERMEDIARIA E DE FUNCIONALIDADES EXTRAS EXCLUEM AUTOMATICAMENTE APOS EXCLUIR O PERFIL
    @Override
    public void execute() {
        if (perfilId == null) {
            throw new IllegalArgumentException("Id do perfil não definido.");
        }

        int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente excluir o perfil \"" + perfilProjetoDeEstimativaRepository.findById(perfilId).getNomePerfil() + "\"?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            perfilNome = perfilProjetoDeEstimativaRepository.findById(perfilId).getNomePerfil();
            boolean removido = perfilProjetoDeEstimativaRepository.deleteById(perfilId);
            
            if (removido) {
                new MostrarMensagemCommand("Perfil \"" + perfilNome + "\" removido com sucesso!").execute();
            } else {
                new MostrarMensagemCommand("Erro ao remover o projeto \"" + perfilProjetoDeEstimativaRepository.findById(perfilId).getNomePerfil() + "\".").execute();
            }
        }      
    }
}
