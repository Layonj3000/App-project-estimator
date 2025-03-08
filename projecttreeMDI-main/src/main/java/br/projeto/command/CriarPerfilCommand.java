/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.PerfilProjetoDeEstimativaPresenter;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.state.escolha_funcionalidades_perfil.InclusaoPerfilProjetoDeEstimativaState;
import br.projeto.view.ManterPerfilProjetoDeEstimativaView;
import javax.swing.JDesktopPane;
/**
 *
 * @author USER
 */
public class CriarPerfilCommand implements Command{
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    //private final JDesktopPane desktop;
    private final UsuarioModel usuarioModel;
    
    public CriarPerfilCommand(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository/*, JDesktopPane desktop*/, UsuarioModel usuarioModel){
    this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
    this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
    //this.desktop = desktop;
    this.usuarioModel = usuarioModel;
    }

    @Override
    public void execute() {
        //INSTANCIAR PRESENTER DO PERFIL
        PerfilProjetoDeEstimativaPresenter perfil = new PerfilProjetoDeEstimativaPresenter(perfilProjetoDeEstimativaRepository, perfilFuncionalidadesPersonalizadasRepository, usuarioModel);
        perfil.setPerfilId(null);
        perfil.setState(new InclusaoPerfilProjetoDeEstimativaState(perfil));
    }
    
}
