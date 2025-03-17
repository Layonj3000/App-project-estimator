package br.projeto.adapter.exportacao;

import br.projeto.presenter.DetalheProjetoPresenter;

public class ExportadorCSVAdapter implements IExportador {

    @Override
    public void exportar(DetalheProjetoPresenter detalhesProjetoPresenter, String caminhoArquivo) throws Exception {
        ExportadorCSV exportadorCSV = new ExportadorCSV();
        exportadorCSV.gerarCSV(detalhesProjetoPresenter, caminhoArquivo);
    }
}