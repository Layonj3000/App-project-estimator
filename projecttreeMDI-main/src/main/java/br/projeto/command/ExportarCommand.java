package br.projeto.command;

import br.projeto.adapter.exportacao.ExportadorCSVAdapter;
import br.projeto.adapter.exportacao.ExportadorPDFAdapter;
import br.projeto.adapter.exportacao.IExportador;
import br.projeto.presenter.DetalheProjetoPresenter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class ExportarCommand implements Command {

    private final String formato;
    private String caminhoArquivo;
    private final DetalheProjetoPresenter detalhesProjetoPresenter;

    public ExportarCommand(String formato, DetalheProjetoPresenter detalhesProjetoPresenter) {
        this.formato = formato;
        this.detalhesProjetoPresenter = detalhesProjetoPresenter;
    }

    @Override
    public void execute() throws RuntimeException{
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filtro = null;
        String extensao = null;

        if ("CSV".equalsIgnoreCase(formato)) {
            filtro = new javax.swing.filechooser.FileNameExtensionFilter("Arquivos CSV (*.csv)", "csv");
            extensao = ".csv";
        } else if ("PDF".equalsIgnoreCase(formato)) {
            filtro = new javax.swing.filechooser.FileNameExtensionFilter("Arquivos PDF (*.pdf)", "pdf");
            extensao = ".pdf";
        } 
        fileChooser.setFileFilter(filtro);
        int escolha = fileChooser.showSaveDialog(null);

        if (escolha == JFileChooser.APPROVE_OPTION) {
            caminhoArquivo = fileChooser.getSelectedFile().getAbsolutePath();
            if (!caminhoArquivo.toLowerCase().endsWith(extensao)) {
                caminhoArquivo += extensao;
            }

            IExportador exportador;
            if ("CSV".equalsIgnoreCase(formato)) {
                exportador = new ExportadorCSVAdapter();
            } else {
                exportador = new ExportadorPDFAdapter();
            }
            try {
                if (caminhoArquivo != null) {
                    exportador.exportar(detalhesProjetoPresenter, caminhoArquivo);
                }
            }catch (Exception ex) {
                Logger.getLogger(ExportarCommand.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}