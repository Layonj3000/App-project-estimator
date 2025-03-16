/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.UsuarioModel;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.state.escolha_perfis_projeto.AEscolhaPerfilState;
import br.projeto.state.escolha_perfis_projeto.AtualizacaoEscolhaPerfilState;
import br.projeto.state.escolha_perfis_projeto.InclusaoEscolhaPerfilState;
import br.projeto.view.EscolhaPlataformaView;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author David Potentini
 */
public class EscolhaPerfilPresenter {
        
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final UsuarioModel usuarioModel;
    
    private Integer idProjeto;
    
    private final EscolhaPlataformaView view;
    
    private AEscolhaPerfilState estado;
    
    public EscolhaPerfilPresenter(ProjetoDeEstimativaRepository projetoDeEstimativaRepository, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository,PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel){
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.usuarioModel = usuarioModel;
        
        this.view = new EscolhaPlataformaView();
    }
    
    public void setEstadoInicial(){
        if(idProjeto != null){
            this.estado = new AtualizacaoEscolhaPerfilState(this, idProjeto);
        }else{
            this.estado = new InclusaoEscolhaPerfilState(this);
        }
    }
    
    public void setIdProjeto(Integer idProjeto){
        this.idProjeto = idProjeto;
    }
    
    public void confirmar(){
        estado.confirmar();
    }
    

    public EscolhaPlataformaView getView() {
        return view;
    }
    
    public ProjetoDeEstimativaRepository getProjetoDeEstimativaRepository() {
        return projetoDeEstimativaRepository;
    }

    public PerfilProjetoDeEstimativaRepository getPerfilProjetoDeEstimativaRepository() {
        return perfilProjetoDeEstimativaRepository;
    }

    public ProjetoFuncionalidadesPersonalizadasRepository getProjetoFuncionalidadesPersonalizadasRepository() {
        return projetoFuncionalidadesPersonalizadasRepository;
    }

    public PerfilFuncionalidadesPersonalizadasRepository getPerfilFuncionalidadesPersonalizadasRepository() {
        return perfilFuncionalidadesPersonalizadasRepository;
    }

    public PerfilProjetoIntermediariaRepository getPerfilProjetoIntermediariaRepository() {
        return perfilProjetoIntermediariaRepository;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }
    
    public JTable getTable(){
        return view.getTable();
    }
    
    public JButton getBtnConfirmar(){
        return view.getBtnConfirmar();
    }
    
}
