package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.repository.UsuarioRepository;
import br.projeto.repository_factory.RepositoryFactory;

public class AlterarFormatoLogCommand implements Command{
    private final UsuarioModel usuarioModel;
    private String formatoSelecionado;
    public AlterarFormatoLogCommand(UsuarioModel usuarioModel, String formatoSelecionado) {
        this.usuarioModel = usuarioModel;
        this.formatoSelecionado =  formatoSelecionado;
    }

    @Override
    public void execute() {
        
        RepositoryFactory factory1 = RepositoryFactory.escolherClasseFabricada(UsuarioRepository.class);
        UsuarioRepository usuarioRepository = factory1.createRepository();
        
        usuarioModel.setFormatoLOG(formatoSelecionado);
        
        usuarioRepository.update(usuarioModel);
        
        System.out.println(usuarioModel.getFormatoLOG());
    }
    
    
}
