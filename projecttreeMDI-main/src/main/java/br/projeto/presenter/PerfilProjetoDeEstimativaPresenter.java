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
import br.projeto.state.APerfilProjetoDeEstimativaState;
import br.projeto.state.ManterPerfilProjetoDeEstimativaState;
import br.projeto.view.ManterPerfilProjetoDeEstimativaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author USER
 */
public class PerfilProjetoDeEstimativaPresenter {
    private PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;//NOVO 
    private PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository;//NOVO
    private UsuarioModel usuarioModel;

    private ManterPerfilProjetoDeEstimativaView view;
    private APerfilProjetoDeEstimativaState estado;
    //ADICIONAR BOTAO PARA EXCLUIR LINHA
    public PerfilProjetoDeEstimativaPresenter(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, PerfilFuncionalidadesPersonalizadasRepository perfilFuncionalidadesPersonalizadasRepository, ManterPerfilProjetoDeEstimativaView view, UsuarioModel usuarioModel) {
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.perfilFuncionalidadesPersonalizadasRepository = perfilFuncionalidadesPersonalizadasRepository;
        this.view = view;
        this.usuarioModel = usuarioModel;
        
        this.estado = new ManterPerfilProjetoDeEstimativaState(this);
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }
    
    public void salvar(){
        estado.salvar();
    }
    
    public void update(){
        estado.update();
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
