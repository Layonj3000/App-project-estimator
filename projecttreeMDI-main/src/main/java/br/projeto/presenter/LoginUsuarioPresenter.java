/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.command.LoginCommand;
import br.projeto.registro_proxy.RegistroProxy;
import br.projeto.view.TelaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author layon
 */
public class LoginUsuarioPresenter {
    private TelaLogin telaLogin;

    public LoginUsuarioPresenter() {
        this.telaLogin = new TelaLogin();
        configurarTela();
    }

    public void configurarTela(){
        telaLogin.setVisible(false);
        telaLogin.getBtnCriarConta().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new RegistroUsuarioPresenter();
                telaLogin.dispose();
            }       
        });
        telaLogin.getBtnEntrar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new LoginCommand(LoginUsuarioPresenter.this).execute();
            }       
        });       
        telaLogin.setVisible(true);
    }    
    public String getEmail(){
        return telaLogin.getTxtEmail().getText().trim();
    }   
    
    public String getSenha(){
        return new String(telaLogin.getSenhaLogin().getPassword());
    }       
    public TelaLogin getView(){
        return telaLogin;
    }
}
