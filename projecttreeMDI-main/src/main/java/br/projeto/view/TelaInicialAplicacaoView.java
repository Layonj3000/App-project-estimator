/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author David Potentini
 */
public class TelaInicialAplicacaoView extends JInternalFrame{
    
    public TelaInicialAplicacaoView() {
        setTitle("Project Estimator ");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(false);
        setResizable(true);
        setSize(1000, 700);

        // Painel principal com cor de fundo
        JPanel painelPrincipal = new JPanel(new GridBagLayout()); // Usando GridBagLayout para centralizar
        painelPrincipal.setBackground(new Color(211, 211, 211));
        add(painelPrincipal);

        // Rótulo do título
        JLabel lblTitulo = new JLabel("Project Estimator");
        lblTitulo.setFont(new Font("Monotype Corsiva", Font.BOLD, 72)); // Fonte duas vezes maior
        lblTitulo.setForeground(Color.WHITE);
        
        // Garantindo que o JLabel não seja cortado
        lblTitulo.setPreferredSize(new Dimension(600, 100)); 
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Adicionando o rótulo centralizado
        painelPrincipal.add(lblTitulo);
    }
}
