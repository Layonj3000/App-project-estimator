package br.projeto.presenter;

import br.projeto.command.AlterarFormatoLogCommand;
import br.projeto.model.UsuarioModel;
import br.projeto.view.TelaConfiguracaoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ConfiguracaoPresenter {
    
    private final TelaConfiguracaoView telaConfiguracaoView;
    private final UsuarioModel usuarioModel;
    
    public ConfiguracaoPresenter(UsuarioModel usuarioModel) {
        this.telaConfiguracaoView = new TelaConfiguracaoView();
        this.usuarioModel = usuarioModel;
        
        configurarTela();
    }
    
    
    public void configurarTela() {
        telaConfiguracaoView.setVisible(false);
        carregarFormatos();
        telaConfiguracaoView.getBtnConfirmar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formatoSelecionado = (String) getFormato().getSelectedItem();
                try {
                    
                    new AlterarFormatoLogCommand(usuarioModel, formatoSelecionado).execute();
                    JOptionPane.showMessageDialog(telaConfiguracaoView, "Formato de LOG alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    telaConfiguracaoView.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(telaConfiguracaoView, "Erro ao alterar LOG: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        telaConfiguracaoView.getBtnVoltar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaConfiguracaoView.dispose();
            }
        });
        telaConfiguracaoView.setVisible(true);
    }
    
    public void carregarFormatos() {
        JComboBox<String> combo = telaConfiguracaoView.getCmEscolherFormato();
        combo.removeAllItems();
        combo.addItem("JSON");
        combo.addItem("CSV");
    }
    
    public JComboBox<String> getFormato() {
        return telaConfiguracaoView.getCmEscolherFormato();
    }
}    

