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
import br.projeto.state.escolha_funcionalidades_perfil.APerfilProjetoDeEstimativaState;
import br.projeto.state.escolha_funcionalidades_perfil.AtualizacaoProjetoDeEstimativaState;
import br.projeto.state.escolha_funcionalidades_perfil.InclusaoPerfilProjetoDeEstimativaState;
import br.projeto.view.ManterPerfilProjetoDeEstimativaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author USER
 */
public class PerfilProjetoDeEstimativaPresenter {
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
    private final PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    private final UsuarioModel usuarioModel;
    private Integer perfilId;

    private ManterPerfilProjetoDeEstimativaView view;
    private APerfilProjetoDeEstimativaState estado;
    //ADICIONAR BOTAO PARA EXCLUIR LINHA
    public PerfilProjetoDeEstimativaPresenter(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, UsuarioModel usuarioModel) {
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.view = new ManterPerfilProjetoDeEstimativaView();
        this.usuarioModel = usuarioModel;

    }
    
    public void setEstadoInicial(){
        if(perfilId != null){
            this.estado = new AtualizacaoProjetoDeEstimativaState(this, perfilId);
        }else{
            this.estado = new InclusaoPerfilProjetoDeEstimativaState(this); 
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
                new AdicionarOpcaoPerfilCommand(PerfilProjetoDeEstimativaPresenter.this).execute();
            }
        }); 
        
    }
    
    public void configurarRemocaoFuncionalidades(){
        view.getBtnRemoverFuncionalidade().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new RemoverOpcaoPerfilCommand(PerfilProjetoDeEstimativaPresenter.this).execute();
            }
        });
    }
    
    public void setState(APerfilProjetoDeEstimativaState estado){
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
