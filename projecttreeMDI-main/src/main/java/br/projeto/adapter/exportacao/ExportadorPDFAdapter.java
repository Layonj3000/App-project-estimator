package br.projeto.adapter.exportacao;

import br.projeto.presenter.DetalheProjetoPresenter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;


public class ExportadorPDFAdapter implements IExportador {
    @Override
    public void exportar(DetalheProjetoPresenter detalhesProjetoPresenter, String caminhoArquivo) throws Exception{
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(caminhoArquivo));
        document.open();
        document.setMargins(36, 36, 36, 36);

        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font fontSubtitulo = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font fontCorpo = new Font(Font.FontFamily.HELVETICA, 10);

        DecimalFormat df = new DecimalFormat("R$ #,##0.00");

        document.add(new Paragraph(detalhesProjetoPresenter.getView().getLblNome().getText(), fontTitulo));
        document.add(new Paragraph(detalhesProjetoPresenter.getView().getLblTipoProjeto().getText(), fontSubtitulo));
        document.add(new Paragraph(detalhesProjetoPresenter.getView().getLblPercentualLucro().getText(), fontSubtitulo));
        document.add(new Paragraph(detalhesProjetoPresenter.getView().getLblPercentualImposto().getText(), fontSubtitulo));
        document.add(new Paragraph("\n"));

        // Centralizando "Funcionalidades"
        PdfPTable tituloTable = new PdfPTable(1);
        tituloTable.setWidthPercentage(100);
        PdfPCell tituloCell = new PdfPCell(new Phrase("Funcionalidades", fontSubtitulo));
        tituloCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tituloCell.setBorder(Rectangle.NO_BORDER);
        tituloTable.addCell(tituloCell);
        document.add(tituloTable);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        PdfPCell cellNome = new PdfPCell(new Phrase("Nome", fontSubtitulo));
        PdfPCell cellDias = new PdfPCell(new Phrase("Dias", fontSubtitulo));
        PdfPCell cellValor = new PdfPCell(new Phrase("Valor", fontSubtitulo));

        cellNome.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellDias.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellValor.setBackgroundColor(BaseColor.LIGHT_GRAY);

        table.addCell(cellNome);
        table.addCell(cellDias);
        table.addCell(cellValor);

        DefaultTableModel modeloTabela = detalhesProjetoPresenter.getView().getModeloTabela();
        for (int i = 0; i < modeloTabela.getRowCount(); i++) {
            table.addCell(new PdfPCell(new Phrase(modeloTabela.getValueAt(i, 0).toString(), fontCorpo)));
            table.addCell(new PdfPCell(new Phrase(modeloTabela.getValueAt(i, 1).toString(), fontCorpo)));
            table.addCell(new PdfPCell(new Phrase(df.format(Double.parseDouble(modeloTabela.getValueAt(i, 2).toString().replace("R$ ", "").replace(",", ""))), fontCorpo)));
        }
        document.add(table);

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Funcionalidades + Custos: " + detalhesProjetoPresenter.getView().getLblValorBase().getText().replace(detalhesProjetoPresenter.getView().getLblValorBase().getText().substring(0, detalhesProjetoPresenter.getView().getLblValorBase().getText().indexOf("R$")),""), fontCorpo));
        document.add(new Paragraph("Imposto: " + detalhesProjetoPresenter.getView().getLblImposto().getText().replace(detalhesProjetoPresenter.getView().getLblImposto().getText().substring(0, detalhesProjetoPresenter.getView().getLblImposto().getText().indexOf("R$")),""), fontCorpo));
        document.add(new Paragraph("Lucro calculado: " + detalhesProjetoPresenter.getView().getLblLucroCalculado().getText().replace(detalhesProjetoPresenter.getView().getLblLucroCalculado().getText().substring(0, detalhesProjetoPresenter.getView().getLblLucroCalculado().getText().indexOf("R$")),""), fontCorpo));
        document.add(new Paragraph("Dias: " + detalhesProjetoPresenter.getView().getLblDias().getText().replace("Dias: ",""), fontCorpo));
        document.add(new Paragraph("Média p/ mês: " + detalhesProjetoPresenter.getView().getLblMediaMes().getText().replace(detalhesProjetoPresenter.getView().getLblMediaMes().getText().substring(0, detalhesProjetoPresenter.getView().getLblMediaMes().getText().indexOf("R$")),""), fontCorpo));
        document.add(new Paragraph("Valor Total: " + detalhesProjetoPresenter.getView().getLblValorTotal().getText().replace(detalhesProjetoPresenter.getView().getLblValorTotal().getText().substring(0, detalhesProjetoPresenter.getView().getLblValorTotal().getText().indexOf("R$")),""), fontCorpo));

        document.close();

    }
}