package br.projeto.command;

import javax.swing.*;

public class MostrarMensagemProjetoCommand implements ProjetoCommand {
    private final String mensagem;

    public MostrarMensagemProjetoCommand(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public void execute() {
        JOptionPane.showMessageDialog(null, mensagem);
    }
}
