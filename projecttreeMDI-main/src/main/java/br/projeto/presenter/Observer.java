package br.projeto.presenter;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.Projeto;
import br.projeto.model.ProjetoDeEstimativaModel;

import java.util.List;

public interface Observer {
    void update(List<Projeto> projetos);
    void updatePerfilModel(List<PerfilProjetoDeEstimativaModel> listaPerfilProjetoDeEstimativaModel);
    void updateProjetoModel(List<ProjetoDeEstimativaModel> listaProjetoDeEstimativaModel);
}
