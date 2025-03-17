package br.projeto.adapter.exportacao;

import br.projeto.presenter.DetalheProjetoPresenter;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.text.DecimalFormat;

public class ExportadorCSV {

    public void gerarCSV(DetalheProjetoPresenter detalhesProjetoPresenter, String caminhoArquivo) throws Exception {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            DecimalFormat df = new DecimalFormat("R$ #,##0.00");

            writer.write("Nome: " + detalhesProjetoPresenter.getView().getLblNome().getText().replace("Nome: ", "") + "\n");
            writer.write("Tipo de Projeto: " + detalhesProjetoPresenter.getView().getLblTipoProjeto().getText().replace("Tipo de Projeto: ", "") + "\n");
            writer.write("Percentual Lucro: " + detalhesProjetoPresenter.getView().getLblPercentualLucro().getText().replace("Percentual Lucro: ", "") + "\n");
            writer.write("Percentual Imposto: " + detalhesProjetoPresenter.getView().getLblPercentualImposto().getText().replace("Percentual Imposto: ", "") + "\n");
            writer.write("\n");

            writer.write("Funcionalidades\n");
            writer.write("Nome,Dias,Valor\n");

            DefaultTableModel modeloTabela = detalhesProjetoPresenter.getView().getModeloTabela();
            for (int i = 0; i < modeloTabela.getRowCount(); i++) {
                String nome = modeloTabela.getValueAt(i, 0).toString();
                String dias = modeloTabela.getValueAt(i, 1).toString();
                String valor = df.format(Double.parseDouble(modeloTabela.getValueAt(i, 2).toString().replace("R$ ", "").replace(",", "")));
                writer.write(nome + "," + dias + "," + valor + "\n");
            }
            writer.write("\n");

            writer.write("Funcionalidades + Custos: " + detalhesProjetoPresenter.getView().getLblValorBase().getText().replace("Funcionalidades + Custos: ", "") + "\n");
            writer.write("Imposto: " + detalhesProjetoPresenter.getView().getLblImposto().getText().replace("Imposto: ", "") + "\n");
            writer.write("Lucro calculado: " + detalhesProjetoPresenter.getView().getLblLucroCalculado().getText().replace("Lucro calculado: ", "") + "\n");
            writer.write("Dias: " + detalhesProjetoPresenter.getView().getLblDias().getText().replace("Dias: ", "") + "\n");
            writer.write("Media p/ mes: " + detalhesProjetoPresenter.getView().getLblMediaMes().getText().replace("Média p/ mês: ", "") + "\n");
            writer.write("Valor Total: " + detalhesProjetoPresenter.getView().getLblValorTotal().getText().replace("Valor Total: ", "") + "\n");
        }
    }
}