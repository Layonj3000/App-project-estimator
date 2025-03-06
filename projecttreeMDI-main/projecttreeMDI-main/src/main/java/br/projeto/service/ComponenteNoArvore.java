package br.projeto.service;


import java.util.List;
import br.projeto.command.Command;

public interface ComponenteNoArvore {
    String obterTexto();

    String obterChaveIcone();

    Command obterComando();

    List<ComponenteNoArvore> obterFilhos();
}
