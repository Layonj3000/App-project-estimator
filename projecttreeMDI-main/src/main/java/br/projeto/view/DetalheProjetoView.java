package br.projeto.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.text.DecimalFormat;

public class DetalheProjetoView extends JInternalFrame {
    private JLabel lblNome, lblCriador, lblData, lblTipoProjeto, lblValorTotal, lblCompartilhadoPor;//PADRAO
    private JLabel lblPercentualLucro, lblPercentualImposto, lblGastoDevDiario;//ADICIONADOS
    private JLabel lblImposto, lblLucroCalculado, lblDias, lblMeses, lblMediaMes, lblValorBase;//TOTALIZADORES ADICIONADOS
    private JTable tabelaDetalhes;
    private DefaultTableModel modeloTabela;

    public DetalheProjetoView() {
        setTitle("Detalhes do Projeto");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(false);
        setResizable(true);
        setSize(1000, 700);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        add(painelPrincipal);

        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setLayout(new GridLayout(3, 2, 10, 15));
        painelCabecalho.setBorder(BorderFactory.createTitledBorder("Informações do Projeto"));

        lblNome = new JLabel("Nome: ");
        lblCriador = new JLabel("Criador: ");
        lblData = new JLabel("Data de Criação: ");
        lblTipoProjeto = new JLabel("Tipo de Projeto: ");
        /*ADICIONADOS*/
        lblCompartilhadoPor = new JLabel("Compartilhado Por: ");
        lblPercentualLucro = new JLabel("Percentual Lucro: ");
        lblPercentualImposto = new JLabel("Percentual Imposto: ");
        lblGastoDevDiario = new JLabel("Valor p/ dia trabalho total: ");
        /*ADICIONADOS*/
        painelCabecalho.add(lblNome);
        painelCabecalho.add(lblData);
        painelCabecalho.add(lblPercentualLucro);
        painelCabecalho.add(lblTipoProjeto);
        painelCabecalho.add(lblGastoDevDiario);
        painelCabecalho.add(lblPercentualImposto);
        painelCabecalho.add(lblCriador);
        painelCabecalho.add(lblCompartilhadoPor);
       

        painelPrincipal.add(painelCabecalho, BorderLayout.NORTH);

        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBorder(BorderFactory.createTitledBorder("Funcionalidades do Projeto"));

        modeloTabela = new DefaultTableModel(new Object[]{"Nome", "Dias", "Valor"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaDetalhes = new JTable(modeloTabela);
        tabelaDetalhes.setFillsViewportHeight(true);

        JScrollPane scrollTabela = new JScrollPane(tabelaDetalhes);
        painelTabela.add(scrollTabela, BorderLayout.CENTER);
        
        /*Para organizar os valores do rodapé*/
        JPanel painelValores = new JPanel();
        painelValores.setLayout(new BoxLayout(painelValores, BoxLayout.X_AXIS)); 
        /*Para organizar os valores do rodapé*/

        JPanel painelValorTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblValorTotal = new JLabel("Valor Total: R$ 0,00");
        lblValorTotal.setFont(new Font("Arial", Font.BOLD, 12));
        painelValorTotal.add(lblValorTotal);
        
        /*Média p/mês no rodapé*/
        JPanel painelMediaMes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblMediaMes = new JLabel("Média p/ mês: R$ 0,00");
        lblMediaMes.setFont(new Font("Arial", Font.BOLD, 12));
        painelMediaMes.add(lblMediaMes);
        /*Média p/mês no rodapé*/
        
        /*Imposto rodapé*/
        JPanel painelImposto = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblImposto = new JLabel("Imposto: R$ 0,00");
        lblImposto.setFont(new Font("Arial", Font.BOLD, 12));
        painelImposto.add(lblImposto);
        /*Imposto rodapé*/        

        /*Lucro calculado rodapé*/
        JPanel painelLucroCalculado = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblLucroCalculado = new JLabel("Lucro calculado: R$ 0,00");
        lblLucroCalculado.setFont(new Font("Arial", Font.BOLD, 12));
        painelLucroCalculado.add(lblLucroCalculado);                
        /*Lucro calculado rodapé*/ 
               
        /*dias totais rodapé*/
        JPanel painelDiasTotais = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblDias = new JLabel("Dias: 0");
        lblDias.setFont(new Font("Arial", Font.BOLD, 12));
        painelDiasTotais.add(lblDias); 
        /*dias totais rodapé*/
        
        /*meses totais rodapé*/
        JPanel painelMesesTotais = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblMeses = new JLabel("Meses: 0");
        lblMeses.setFont(new Font("Arial", Font.BOLD, 12));
        painelMesesTotais.add(lblMeses); 
        /*meses totais rodapé*/
        
        JPanel painalTotalBase = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblValorBase = new JLabel("Funcionalidades + Custos: ");
        lblValorBase.setFont(new Font("Arial", Font.BOLD, 12));
        painalTotalBase.add(lblValorBase);
        
        /*Adicionando os paineis de rodapé ao painelValores*/
        painelValores.add(painalTotalBase);
        painelValores.add(painelImposto);
        painelValores.add(painelLucroCalculado);
        painelValores.add(painelDiasTotais);
        painelValores.add(painelMesesTotais);
        painelValores.add(painelMediaMes);
        painelValores.add(painelValorTotal);
        /*Adicionando os paineis de rodapé ao painelValores*/
        
        //painelTabela.add(painelValorTotal, BorderLayout.SOUTH);//ANTES
        painelTabela.add(painelValores, BorderLayout.SOUTH);//NOVO
        painelPrincipal.add(painelTabela, BorderLayout.CENTER);
    }

    public void atualizarCabecalho(String nome, String criador, Date data, String tipoProjeto, String percentualLucro, String percentualImpostos, String totalDevDiario, String compartilhadoPor) {
        lblNome.setText("Nome: " + nome);                                                                      
        lblCriador.setText("Criador: " + criador);
        lblCompartilhadoPor.setText("Compartilhado Por: " + compartilhadoPor);
        lblData.setText("Data de Criação: " + data);
        lblTipoProjeto.setText("Tipo de Projeto: " + tipoProjeto);
        lblPercentualLucro.setText("Percentual Lucro: " + percentualLucro + "%");
        lblPercentualImposto.setText("Percentual Imposto: " + percentualImpostos + "%");
        lblGastoDevDiario.setText("Valor p/ dia trabalho total: " +"R$"+ totalDevDiario);
    }

    public void atualizarTabela(Object[][] dados) {
        modeloTabela.setRowCount(0);
        for (Object[] linha : dados) {
            modeloTabela.addRow(linha);
        }

    }
    
    public void atualizarRodape(  int totalDias, double totalMeses, double totalImpostos, double LucroCalculado, double mediaMes, double valorTotal, double totalBase){
        DecimalFormat df = new DecimalFormat("R$ #,##0.00");
        DecimalFormat df2 = new DecimalFormat("#.00");
        
        lblValorTotal.setText("Valor Total: " + df.format(valorTotal));

        lblDias.setText("Dias: " + totalDias);

        lblMeses.setText("Meses: " + df2.format(totalMeses));

        lblImposto.setText("Imposto: " + df.format(totalImpostos));

        lblLucroCalculado.setText("Lucro calculado: " + df.format(LucroCalculado));
        
        lblMediaMes.setText("Média p/ mês: " + df.format(mediaMes));
        
        lblValorBase.setText("Funcionalidades + Custos: " + df.format(totalBase));
    }
    
    public DefaultTableModel getModeloTabela() {
    return modeloTabela;
    }
}
