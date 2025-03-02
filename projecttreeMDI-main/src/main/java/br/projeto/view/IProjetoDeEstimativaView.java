/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.projeto.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author David Potentini
 */
public interface IProjetoDeEstimativaView{
    //CONSIDERAR RETIRADA DO VOLTAR E CONFIRMAR POIS SOMENTE O GETFRAME BASTA PARA UTILIZAR OS METODOS DA VIEW DESEJADA
    public JButton getBtnConfirmar();

    public JButton getBtnVoltar();
    
    public JTable getTable();
    
    public JFrame getFrame();
}
