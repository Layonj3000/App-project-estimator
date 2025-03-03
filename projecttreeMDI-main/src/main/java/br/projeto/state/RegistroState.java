/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.state;

import br.projeto.view.TelaRegistro;

/**
 *
 * @author layon
 */
public class RegistroState implements TelaState{
    TelaRegistro telaRegistro = new TelaRegistro();
    @Override
    public void exibirTela() {
        telaRegistro.setVisible(true);
    }
}
