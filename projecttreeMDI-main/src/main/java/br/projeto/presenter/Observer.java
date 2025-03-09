package br.projeto.presenter;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.PerfilProjetoIntermediariaModel;
import br.projeto.model.Projeto;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;

import java.util.List;
import javax.swing.JOptionPane;

public abstract class Observer {
    public void update(List<Projeto> projetos){
        JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL REALIZAR O UPDATE");//IDENTIFICAR O NOME DO ESTADO
        throw new RuntimeException();
    };
    
    public void updatePerfilModel(List<PerfilProjetoDeEstimativaModel> listaPerfilProjetoDeEstimativaModel){
        JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL REALIZAR O UPDATE");//IDENTIFICAR O NOME DO ESTADO
        throw new RuntimeException();
    };
    
    public void updateProjetoModel(List<ProjetoDeEstimativaModel> listaProjetoDeEstimativaModel){
        JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL REALIZAR O UPDATE");//IDENTIFICAR O NOME DO ESTADO
        throw new RuntimeException();
    };
    
    public void updateProjetoFuncionalidadesPersonalizadasModel(List<ProjetosFuncionalidadesPersonalizadasModel> listaProjetosFuncionalidadesPersonalizadasModel){
        JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL REALIZAR O UPDATE");//IDENTIFICAR O NOME DO ESTADO
        throw new RuntimeException();
    };
    
    public void updatePerfilFuncionalidadesPersonalizadasModel(List<PerfilFuncionalidadesPersonalizadasModel> listaPerfilFuncionalidadesPersonalizadasModel){
        JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL REALIZAR O UPDATE");//IDENTIFICAR O NOME DO ESTADO
        throw new RuntimeException();
    };
    
    public void updatePerfilProjetoIntermediariaModel(List<PerfilProjetoIntermediariaModel> listaPerfilProjetoIntermediariaModel){
        JOptionPane.showMessageDialog(null, "NÃO É POSSIVEL REALIZAR O UPDATE");//IDENTIFICAR O NOME DO ESTADO
        throw new RuntimeException();
    };
}
