package br.projeto.adapter.exportacao;

import br.projeto.presenter.DetalheProjetoPresenter;

public interface IExportador {
    void exportar(DetalheProjetoPresenter detalhesProjetoPresenter)throws Exception ;
}