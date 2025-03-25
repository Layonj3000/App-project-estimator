package br.projeto.adapter.exportacao;

import br.projeto.presenter.DetalheProjetoPresenter;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

public class ExportadorCSVAdapter implements IExportador {
    
    private final String caminhoArquivo;

    public ExportadorCSVAdapter() throws IOException {
        this.caminhoArquivo = escolherCaminho("csv", "Arquivos CSV (*.csv)");
    }
    @Override
    public void exportar(DetalheProjetoPresenter detalhesProjetoPresenter) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {

            fos.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});

            DecimalFormat df = new DecimalFormat("R$ #,##0.00");

            writer.write("Nome: " + detalhesProjetoPresenter.getView().getLblNome().getText().replace("Nome: ", "") + "\n");
            writer.write("Tipo de Projeto: " + detalhesProjetoPresenter.getView().getLblTipoProjeto().getText().replace("Tipo de Projeto: ", "") + "\n");
            writer.write("Percentual Lucro: " + detalhesProjetoPresenter.getView().getLblPercentualLucro().getText().replace("Percentual Lucro: ", "") + "\n");
            writer.write("Percentual Imposto: " + detalhesProjetoPresenter.getView().getLblPercentualImposto().getText().replace("Percentual Imposto: ", "") + "\n");
            writer.write("\n");

            writer.write("Funcionalidades\n");
            writer.write("Nome;Dias;Valor\n"); // Alterado para ponto e vírgula

            DefaultTableModel modeloTabela = detalhesProjetoPresenter.getView().getModeloTabela();
            for (int i = 0; i < modeloTabela.getRowCount(); i++) {
                String nome = modeloTabela.getValueAt(i, 0).toString();
                String dias = modeloTabela.getValueAt(i, 1).toString();
                String valor = df.format(Double.parseDouble(modeloTabela.getValueAt(i, 2).toString().replace("R$ ", "").replace(",", "")));
                writer.write(nome + ";" + dias + ";" + valor + "\t\n"); // Alterado para ponto e vírgula
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
    private String escolherCaminho(String extensao, String descricao) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(descricao, extensao));
        int escolha = fileChooser.showSaveDialog(null);

        if (escolha != JFileChooser.APPROVE_OPTION) {
            throw new IOException("Nenhum arquivo selecionado.");
        }

        String caminho = fileChooser.getSelectedFile().getAbsolutePath();
        return caminho.toLowerCase().endsWith("." + extensao) ? caminho : caminho + "." + extensao;
    }
}
