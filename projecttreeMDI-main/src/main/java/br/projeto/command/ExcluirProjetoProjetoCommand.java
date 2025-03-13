package br.projeto.command;

import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoRepositoryMock;

import javax.swing.*;

public class ExcluirProjetoProjetoCommand implements Command {
    private /*final*/ ProjetoRepositoryMock repository;//FINAL COMENTADO SOMENTE PARA NAO PRECISAR INICIALIZAR NO NOVO CONTRUTOR CRIADO
    private ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private Integer projetoId;
    private String projetoNome;//ATRIBUTO ANTIGO
    
    public ExcluirProjetoProjetoCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository){//CONSTRUTOR NOVO 1
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
    }
    
    public ExcluirProjetoProjetoCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository, Integer projetoId){//CONSTRUTOR NOVO 2 PARA METODO adicionarMenuContextual() DE PrincipalPresenter
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.projetoId = projetoId;
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
            new MostrarMensagemProjetoCommand("Nome do projeto não definido.").execute();
            return;
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
                new MostrarMensagemProjetoCommand("Projeto \"" + projetoNome + "\" removido com sucesso!").execute();
            } else {
                new MostrarMensagemProjetoCommand("Erro ao remover o projeto \"" + projetoDeEstimativaRepository.findById(projetoId).getNomeProjetoDeEstimativa() + "\".").execute();
            }
        }   
    }
}
