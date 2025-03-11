/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;

/**
 *
 * @author USER
 */
public class DetalhePerfilView extends JInternalFrame {
    private JLabel lblNome, lblCriador, lblData;//PADRAO
    private JLabel lblTaxaDesign, lblTaxaDesenvolvimento, lblTaxaGerenciaProjeto;//ADICIONADOS
    private JTable tabelaDetalhes;
    private DefaultTableModel modeloTabela;

    public DetalhePerfilView() {
        setTitle("Detalhes do Perfil");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(false);
        setResizable(true);
        setSize(1000, 700);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        add(painelPrincipal);

        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setLayout(new GridLayout(3, 2, 10, 15));
        painelCabecalho.setBorder(BorderFactory.createTitledBorder("Informações do Perfil"));

        lblNome = new JLabel("Nome: ");
        lblData = new JLabel("Data de Criação: ");
        lblCriador = new JLabel("Criador: ");
        lblTaxaDesign = new JLabel("Taxa Diária Design: ");
        lblTaxaDesenvolvimento = new JLabel("Taxa Diária Desenvolvimento: ");
        lblTaxaGerenciaProjeto = new JLabel("Taxa Diária Gerência Projeto: ");
        /*lblTipoProjeto = new JLabel("Tipo de Projeto: ");
        lblStatus = new JLabel("Status: ");*/

        painelCabecalho.add(lblNome);
        painelCabecalho.add(lblTaxaDesenvolvimento);
        painelCabecalho.add(lblData);
        painelCabecalho.add(lblTaxaDesign);
        painelCabecalho.add(lblCriador);
        painelCabecalho.add(lblTaxaGerenciaProjeto);

        painelPrincipal.add(painelCabecalho, BorderLayout.NORTH);

        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBorder(BorderFactory.createTitledBorder("Funcionalidades do Perfil"));

        modeloTabela = new DefaultTableModel(new Object[]{"Nome", "Dias"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaDetalhes = new JTable(modeloTabela);
        tabelaDetalhes.setFillsViewportHeight(true);

        JScrollPane scrollTabela = new JScrollPane(tabelaDetalhes);
        painelTabela.add(scrollTabela, BorderLayout.CENTER);

        /*JPanel painelValorTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblValorTotal = new JLabel("Valor Total: R$ 0,00");
        lblValorTotal.setFont(new Font("Arial", Font.BOLD, 14));
        painelValorTotal.add(lblValorTotal);*/

        /*painelTabela.add(painelValorTotal, BorderLayout.SOUTH);*/
        painelPrincipal.add(painelTabela, BorderLayout.CENTER);
    }

    public void atualizarCabecalho(String nome, String criador, Date data, String taxaDev, String taxaDes, String taxaGer) {
        lblNome.setText("Nome: " + nome);                                                                          
        lblCriador.setText("Criador: " + criador);
        lblData.setText("Data de Criação: " + data);
        lblTaxaDesenvolvimento.setText("Taxa Diária Desenvolvimento: " + taxaDev);
        lblTaxaDesign.setText("Taxa Diária Design: " + taxaDes);
        lblTaxaGerenciaProjeto.setText("Taxa Diária Gerência Projeto: " + taxaGer);
    }

    public void atualizarTabela(Object[][] dados/*, double valorTotal*/) {
        modeloTabela.setRowCount(0);
        for (Object[] linha : dados) {
            modeloTabela.addRow(linha);
        }
        /*DecimalFormat df = new DecimalFormat("R$ #,##0.00");
        lblValorTotal.setText("Valor Total: " + df.format(valorTotal));*/
    }    
}
