package br.projeto.adapter.exportacao;

import br.projeto.presenter.DetalheProjetoPresenter;

public class ExportadorPDFAdapter implements IExportador {

    @Override
    public void exportar(DetalheProjetoPresenter detalhesProjetoPresenter, String caminhoArquivo) throws Exception {
        ExportadorPDF exportadorPDF = new ExportadorPDF();
        exportadorPDF.gerarPDF(detalhesProjetoPresenter, caminhoArquivo);
    }
}