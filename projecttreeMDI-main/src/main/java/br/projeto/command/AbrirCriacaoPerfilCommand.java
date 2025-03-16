/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
/**
 *
 * @author David Potentini
 */
public class AbrirCriacaoPerfilCommand implements Command{
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final UsuarioModel usuarioModel;
    
    public AbrirCriacaoPerfilCommand(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository/*, JDesktopPane desktop*/, UsuarioModel usuarioModel){
    this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
    this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
    this.usuarioModel = usuarioModel;
    }

    @Override
    public void execute() {
        EscolhaFuncionalidadesPerfilPresenter perfil = new EscolhaFuncionalidadesPerfilPresenter(perfilProjetoDeEstimativaRepository, perfilFuncionalidadesPersonalizadasRepository, usuarioModel);
        perfil.setPerfilId(null);
        perfil.setEstadoInicial();
    }
    
}
