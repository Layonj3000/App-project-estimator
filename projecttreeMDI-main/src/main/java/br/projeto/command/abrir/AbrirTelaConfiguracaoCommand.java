
package br.projeto.command.abrir;

import br.projeto.command.Command;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.ConfiguracaoPresenter;

public class AbrirTelaConfiguracaoCommand implements Command{

    private final UsuarioModel usuarioModel;

    public AbrirTelaConfiguracaoCommand(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }
    
    @Override
    public void execute() {
        new ConfiguracaoPresenter(usuarioModel);
    }
    
}
