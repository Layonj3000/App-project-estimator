package br.projeto.presenter;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.PerfilProjetoIntermediariaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import br.projeto.model.UsuarioModel;

import java.util.List;
import javax.swing.JOptionPane;

public abstract class Observer {

    public void updatePerfilModel(List<PerfilProjetoDeEstimativaModel> listaPerfilProjetoDeEstimativaModel){
        showErrorMessage();
    }

    public void updateProjetoModel(List<ProjetoDeEstimativaModel> listaProjetoDeEstimativaModel){
        showErrorMessage();
    }

    public void updateProjetoFuncionalidadesPersonalizadasModel(List<ProjetosFuncionalidadesPersonalizadasModel> listaProjetosFuncionalidadesPersonalizadasModel){
        showErrorMessage();
    }

    public void updatePerfilFuncionalidadesPersonalizadasModel(List<PerfilFuncionalidadesPersonalizadasModel> listaPerfilFuncionalidadesPersonalizadasModel){
        showErrorMessage();
    }

    public void updatePerfilProjetoIntermediariaModel(List<PerfilProjetoIntermediariaModel> listaPerfilProjetoIntermediariaModel){
        showErrorMessage();
    }

    public void updateUsuarioModel(UsuarioModel usuarioModel){
        showErrorMessage();
    }

    private void showErrorMessage() {
        String className = this.getClass().getSimpleName();
        JOptionPane.showMessageDialog(null, "NÃO É POSSÍVEL REALIZAR O UPDATE - Estado atual: " + className);
        throw new RuntimeException("Método update não implementado em " + className);
    }
}
