/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.command.CompartilharCommand;
import br.projeto.view.TelaCompartilhar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.projeto.model.UsuarioModel;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
/**
 *
 * @author layon
 */
public class CompartilharPresenter {
    private TelaCompartilhar telaCompartilhar;
    private final Integer projetoID;
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final UsuarioModel usuarioModel;
    
    public CompartilharPresenter(PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository, ProjetoDeEstimativaRepository projetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository, PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel, Integer projetoID) {
        this.telaCompartilhar = new TelaCompartilhar();
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.usuarioModel = usuarioModel;
        this.projetoID = projetoID;
        configurarTela();
    }

    public void configurarTela(){
        telaCompartilhar.setVisible(false);
        telaCompartilhar.getBtnConfirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                CompartilharCommand compartilharCommand = new CompartilharCommand(CompartilharPresenter.this,perfilProjetoDeEstimativaRepository, projetoDeEstimativaRepository,projetoFuncionalidadesPersonalizadasRepository, perfilProjetoIntermediariaRepository, usuarioModel, projetoID);
                compartilharCommand.execute();                
            }       
        });
        telaCompartilhar.getBtnVoltar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                telaCompartilhar.dispose();
            }       
        });       
        telaCompartilhar.setVisible(true);
    }    
    public String getEmail(){
        return telaCompartilhar.getTxtEmail().getText().trim();
    }   
  
    public TelaCompartilhar getView(){
        return telaCompartilhar;
    }  
}
    

