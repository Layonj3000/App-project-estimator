package br.projeto.command;

import br.projeto.adapter.exportacao.ExportadorCSVAdapter;
import br.projeto.adapter.exportacao.ExportadorPDFAdapter;
import br.projeto.adapter.exportacao.IExportador;
import br.projeto.presenter.DetalheProjetoPresenter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportarCommand implements Command {

    private final IExportador exportador;
    private final DetalheProjetoPresenter detalhesProjetoPresenter;

    public ExportarCommand(String formato, DetalheProjetoPresenter detalhesProjetoPresenter) throws IOException {
        this.detalhesProjetoPresenter = detalhesProjetoPresenter;

        if ("CSV".equalsIgnoreCase(formato)) {
            this.exportador = new ExportadorCSVAdapter();
        } else {
            this.exportador = new ExportadorPDFAdapter();
        }
    }

    @Override
    public void execute() {
        try {
            exportador.exportar(detalhesProjetoPresenter);
        } catch (Exception ex) {
            Logger.getLogger(ExportarCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
