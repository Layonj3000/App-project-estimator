package br.projeto.service;

import br.projeto.command.ProjetoCommand;

import java.util.List;

public interface ComponenteNoArvore {
    String obterTexto();

    String obterChaveIcone();

    ProjetoCommand obterComando();

    List<ComponenteNoArvore> obterFilhos();
}
