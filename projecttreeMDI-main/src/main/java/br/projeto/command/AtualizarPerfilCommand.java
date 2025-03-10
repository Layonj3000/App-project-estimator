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
 * @author USER
 */
public class AtualizarPerfilCommand implements Command{
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    private final UsuarioModel usuarioModel;
    
    private Integer idPerfil;

    public AtualizarPerfilCommand(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, UsuarioModel usuarioModel) {
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.usuarioModel = usuarioModel;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public void execute() {
        EscolhaFuncionalidadesPerfilPresenter perfil = new EscolhaFuncionalidadesPerfilPresenter(perfilProjetoDeEstimativaRepository,  perfilFuncionalidadesPersonalizadasRepository, usuarioModel);
        perfil.setPerfilId(idPerfil);
        perfil.setEstadoInicial();
    }
    
    
    
    
}
