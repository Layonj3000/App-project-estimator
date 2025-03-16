package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.observer.LogNotifier;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import com.log.model.LogRegister;

import javax.swing.*;

public class ExcluirProjetoCommand extends ProjetoLogCommand {
    private ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private Integer projetoId;
    private String projetoNome;//ATRIBUTO ANTIGO
    private final UsuarioModel usuarioModel;
    
    public ExcluirProjetoCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository, UsuarioModel usuarioModel, LogNotifier logNotifier){//CONSTRUTOR NOVO 1
        super(logNotifier, usuarioModel.getFormatoLOG());
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.usuarioModel = usuarioModel;
    }
    
    public ExcluirProjetoCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository, Integer projetoId,UsuarioModel usuarioModel, LogNotifier logNotifier){//CONSTRUTOR NOVO 2 PARA METODO adicionarMenuContextual() DE PrincipalPresenter
        super(logNotifier, usuarioModel.getFormatoLOG());
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.projetoId = projetoId;
        this.usuarioModel = usuarioModel;
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

        LogRegister logRegister = new LogRegister("Exclusão de Projeto", usuarioModel.getNome(),
                usuarioModel.getEmail(), true, "Sucesso");

        super.setLogRegister(logRegister);
        super.execute();
    }
}
