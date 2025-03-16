package br.projeto.presenter;

import br.projeto.command.ExportarCommand;
import br.projeto.view.TelaExportacaoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class ExportarPresenter {
    private final TelaExportacaoView telaExportacaoView;

    public ExportarPresenter() {
        this.telaExportacaoView = new TelaExportacaoView();
        configurarTela();
    }

    public void configurarTela(){
        telaExportacaoView.setVisible(false);
        telaExportacaoView.getBtnConfirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String formatoSelecionado = (String) getFormato().getSelectedItem();
                new ExportarCommand(formatoSelecionado).execute();

            }       
        });
        telaExportacaoView.getBtnVoltar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                telaExportacaoView.dispose();
            }       
        });       
        telaExportacaoView.setVisible(true);
    }    
    public JComboBox<String> getFormato(){
        return telaExportacaoView.getCmEscolherFormato();
    }   
          
    public TelaExportacaoView getView(){
        return telaExportacaoView;
    }    
}
