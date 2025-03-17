package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.perfil_builder.BuilderObserver;
import br.projeto.presenter.RegistroUsuarioPresenter;
import br.projeto.repository.UsuarioRepository;
import br.projeto.repository_factory.RepositoryFactory;

public class RegistroCommand implements Command {
    
    private final RegistroUsuarioPresenter registroUsuarioPresenter;

    public RegistroCommand(RegistroUsuarioPresenter registroUsuarioPresenter) {
        this.registroUsuarioPresenter = registroUsuarioPresenter;
    }

    @Override
    public void execute() {
        
        RepositoryFactory factory1 = RepositoryFactory.escolherClasseFabricada(UsuarioRepository.class);
        UsuarioRepository usuarioRepository = factory1.createRepository();

        UsuarioModel novoUsuario = new UsuarioModel(null, registroUsuarioPresenter.getNomeUsuario(), registroUsuarioPresenter.getSenha(), registroUsuarioPresenter.getEmail(), "CSV");
        
        new BuilderObserver(usuarioRepository);
        usuarioRepository.insert(novoUsuario);
        
    }
}