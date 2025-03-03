/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state;

import br.projeto.view.TelaLogin;

/**
 *
 * @author layon
 */
public class LoginState implements TelaState{
    TelaLogin telaLogin = new TelaLogin();
    @Override
    public void exibirTela() {
        telaLogin.setVisible(true);
    }
  
}
