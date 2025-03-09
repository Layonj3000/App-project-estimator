package br.projeto.service;


import javax.swing.*;
import java.util.Map;
import br.projeto.command.Command;

public class CriarBarraBotaoService {
    private final Map<String, Command> comandos;

    public CriarBarraBotaoService(Map<String, Command> comandos) {
        this.comandos = comandos;
    }

    public JToolBar criarBarraDeBotoes() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        adicionarBotao(toolBar, "Dashboard", "principal", "Principal");
        adicionarBotao(toolBar, "Novo Projeto", "projeto", "Novo projeto");
        adicionarBotao(toolBar, "Novo Perfil", "perfil", "Novo perfil");//mudar ícone
        adicionarBotao(toolBar, "Usuário", "usuario", "Usuário");

        return toolBar;
    }

    private void adicionarBotao(JToolBar toolBar, String texto, String iconeKey, String comandoChave) {
        JButton botao = new JButton(texto);
        botao.setIcon(IconService.getIcon(iconeKey));
        botao.addActionListener(e -> {
            Command comando = comandos.get(comandoChave);
            if (comando == null) {
                throw new IllegalArgumentException("Comando não encontrado: " + comandoChave);
            }
            comando.execute();
        });
        toolBar.add(botao);
    }
}
