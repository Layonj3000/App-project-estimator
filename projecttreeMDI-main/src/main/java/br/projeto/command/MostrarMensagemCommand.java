package br.projeto.command;

import javax.swing.*;

public class MostrarMensagemCommand implements Command {
    private final String mensagem;

    public MostrarMensagemCommand(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public void execute() {
        JOptionPane.showMessageDialog(null, mensagem);
    }
}
