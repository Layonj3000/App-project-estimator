package br.projeto.presenter;

import br.projeto.command.LoginCommand;
import br.projeto.view.TelaLoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public final class LoginUsuarioPresenter {
    private final TelaLoginView telaLogin;

    public LoginUsuarioPresenter() {
        this.telaLogin = new TelaLoginView();
        configurarTela();
    }

    public void configurarTela(){
        telaLogin.setVisible(false);
        telaLogin.getBtnCriarConta().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new RegistroUsuarioPresenter();
                telaLogin.dispose();
            }       
        });
        telaLogin.getBtnEntrar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try { 
                    new LoginCommand(LoginUsuarioPresenter.this).execute();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(telaLogin, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }       
        });       
        telaLogin.setVisible(true);
    }    
    public String getEmail(){
        return telaLogin.getTxtEmail().getText().trim();
    }   
    
    public String getSenha(){
        return new String(telaLogin.getSenhaLogin().getPassword());
    }       
    public TelaLoginView getView(){
        return telaLogin;
    }
}
