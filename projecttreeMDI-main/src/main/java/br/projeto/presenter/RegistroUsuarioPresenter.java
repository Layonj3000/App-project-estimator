/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.command.RegistroCommand;
import br.projeto.registro_proxy.IRegistroProxy;
import br.projeto.registro_proxy.RegistroProxy;
import br.projeto.view.TelaRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author layon
 */
public class RegistroUsuarioPresenter implements IRegistroProxy{
    private TelaRegistro telaRegistro;
    private RegistroProxy proxy;
    
    public RegistroUsuarioPresenter(){
        this.telaRegistro = new TelaRegistro();
        this.proxy = new RegistroProxy(this);
        configurarTela();
    }

    public TelaRegistro getTelaRegistro() {
        return telaRegistro;
    }
    
    private void configurarTela(){
        telaRegistro.setVisible(false);
        telaRegistro.getBtnRegistrar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               proxy.registrar();
            }       
        });
        telaRegistro.getBtnVoltar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new LoginUsuarioPresenter();
                telaRegistro.dispose();
            }       
        });        
        telaRegistro.setVisible(true);
    }
    
    @Override
    public void registrar(){
        new RegistroCommand(this).execute();
    }
    
    public String getNomeUsuario(){
        return telaRegistro.getTxtNome().getText().trim();
    }
    
    public String getEmail(){
        return telaRegistro.getTxtEmail().getText().trim();
    }   
    
    public String getSenha(){
        return new String(telaRegistro.getPwSenha().getPassword());
    }    
    public TelaRegistro getView(){
        return telaRegistro;
    }
}
