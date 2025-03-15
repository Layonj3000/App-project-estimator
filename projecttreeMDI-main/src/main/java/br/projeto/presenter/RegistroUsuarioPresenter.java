package br.projeto.presenter;

import br.projeto.command.RegistroCommand;
import br.projeto.registro_proxy.IRegistroProxy;
import br.projeto.registro_proxy.RegistroProxy;
import br.projeto.view.TelaRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class RegistroUsuarioPresenter implements IRegistroProxy {
    private final TelaRegistro telaRegistro;
    private final RegistroProxy proxy;
    
    public RegistroUsuarioPresenter() {
        this.telaRegistro = new TelaRegistro();
        this.proxy = new RegistroProxy(this);
        configurarTela();
    }

    public TelaRegistro getTelaRegistro() {
        return telaRegistro;
    }
    
    private void configurarTela() {
        telaRegistro.setVisible(false);
        telaRegistro.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    proxy.registrar();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }       
        });

        telaRegistro.getBtnVoltar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginUsuarioPresenter();
                telaRegistro.dispose();
            }       
        });        
        telaRegistro.setVisible(true);
    }
    
    @Override
    public void registrar() {
        try {
            new RegistroCommand(this).execute();
            JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new LoginUsuarioPresenter();
            telaRegistro.dispose();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                 "Ocorreu um erro inesperado. Tente novamente.", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }    
    
    public String getNomeUsuario() {
        return telaRegistro.getTxtNome().getText().trim();
    }
    
    public String getEmail() {
        return telaRegistro.getTxtEmail().getText().trim();
    }   
    
    public String getSenha() {
        return new String(telaRegistro.getPwSenha().getPassword());
    }    

    public TelaRegistro getView() {
        return telaRegistro;
    }
}