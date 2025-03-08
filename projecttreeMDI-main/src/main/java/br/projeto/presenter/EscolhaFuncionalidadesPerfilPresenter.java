/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.command.AdicionarOpcaoPerfilCommand;
import br.projeto.command.RemoverOpcaoPerfilCommand;
import br.projeto.model.UsuarioModel;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.state.escolha_funcionalidades_perfil.AEscolhaFuncionalidadesPerfilState;
import br.projeto.state.escolha_funcionalidades_perfil.AtualizacaoEscolhaFuncionalidadesPerfilState;
import br.projeto.state.escolha_funcionalidades_perfil.InclusaoEscolhaFuncionalidadesPerfilState;
import br.projeto.view.ManterPerfilProjetoDeEstimativaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author USER
 */
public class EscolhaFuncionalidadesPerfilPresenter {
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    private final UsuarioModel usuarioModel;
    private Integer perfilId;

    private ManterPerfilProjetoDeEstimativaView view;
    private AEscolhaFuncionalidadesPerfilState estado;
    //ADICIONAR BOTAO PARA EXCLUIR LINHA
    public EscolhaFuncionalidadesPerfilPresenter(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, UsuarioModel usuarioModel) {
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.view = new ManterPerfilProjetoDeEstimativaView();
        this.usuarioModel = usuarioModel;

    }
    
    public void setEstadoInicial(){
        if(perfilId != null){
            this.estado = new AtualizacaoEscolhaFuncionalidadesPerfilState(this, perfilId);
        }else{
            this.estado = new InclusaoEscolhaFuncionalidadesPerfilState(this); 
        }
    }
    
    public Integer getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }

    
    
    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }
    
    public void salvar(){
        estado.salvar();
    }
    
    public void voltar(){
        estado.voltar();
    }
    
    public void configurarAdicaoFuncionalidades(){
        view.getBtnAdicionarFuncionalidade().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new AdicionarOpcaoPerfilCommand(EscolhaFuncionalidadesPerfilPresenter.this).execute();
            }
        }); 
        
    }
    
    public void configurarRemocaoFuncionalidades(){
        view.getBtnRemoverFuncionalidade().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new RemoverOpcaoPerfilCommand(EscolhaFuncionalidadesPerfilPresenter.this).execute();
            }
        });
    }
    
    public void setState(AEscolhaFuncionalidadesPerfilState estado){
        this.estado = estado;
    }

    public ManterPerfilProjetoDeEstimativaView getView() {
        return view;
    }

    public PerfilProjetoDeEstimativaRepository getPerfilProjetoDeEstimativaRepository() {
        return perfilProjetoDeEstimativaRepository;
    }

    public PerfilFuncionalidadesPersonalizadasRepository getPerfilFuncionalidadesPersonalizadasRepository() {
        return perfilFuncionalidadesPersonalizadasRepository;
    }
    
}
