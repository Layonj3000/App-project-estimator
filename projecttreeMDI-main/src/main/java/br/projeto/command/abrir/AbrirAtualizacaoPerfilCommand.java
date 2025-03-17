/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command.abrir;

import br.projeto.command.Command;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.EscolhaFuncionalidadesPerfilPresenter;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;

/**
 *
 * @author David Potentini
 */
public class AbrirAtualizacaoPerfilCommand implements Command{
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;
    private final UsuarioModel usuarioModel;
    
    private Integer idPerfil;

    public AbrirAtualizacaoPerfilCommand(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, UsuarioModel usuarioModel) {
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
