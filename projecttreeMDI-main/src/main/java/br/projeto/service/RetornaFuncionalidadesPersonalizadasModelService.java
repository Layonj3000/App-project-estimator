/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David Potentini
 */
public class RetornaFuncionalidadesPersonalizadasModelService {
    private static RetornaFuncionalidadesPersonalizadasModelService service = null;
    
    private RetornaFuncionalidadesPersonalizadasModelService(){}
    
    public static RetornaFuncionalidadesPersonalizadasModelService getInstancia(){
        if(service == null){
            service = new RetornaFuncionalidadesPersonalizadasModelService();
        }
        return service;
    }
    
    public PerfilFuncionalidadesPersonalizadasModel instantiatePerfilFuncionalidadesPersonalizadasModel(ResultSet rs, PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel) throws SQLException {
        PerfilFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = new PerfilFuncionalidadesPersonalizadasModel();
        projetosFuncionalidadesPersonalizadasModel.setId(rs.getInt("id"));
        projetosFuncionalidadesPersonalizadasModel.setNome(rs.getString("nome"));
        projetosFuncionalidadesPersonalizadasModel.setValor(rs.getInt("valor"));
        projetosFuncionalidadesPersonalizadasModel.setPerfilProjetoDeEstimativaModel(perfilProjetoDeEstimativaModel);
        
        return projetosFuncionalidadesPersonalizadasModel;
    }
    
    public ProjetosFuncionalidadesPersonalizadasModel instantiateProjetosFuncionalidadesPersonalizadasModel(ResultSet rs, ProjetoDeEstimativaModel projetoDeEstimativaModel) throws SQLException {
        ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = new ProjetosFuncionalidadesPersonalizadasModel();
        projetosFuncionalidadesPersonalizadasModel.setId(rs.getInt("id"));
        projetosFuncionalidadesPersonalizadasModel.setNome(rs.getString("nome"));
        projetosFuncionalidadesPersonalizadasModel.setSelecionado((rs.getInt("selecionado")));
        projetosFuncionalidadesPersonalizadasModel.setProjetoDeEstimativaModel(projetoDeEstimativaModel);
        
        return projetosFuncionalidadesPersonalizadasModel;
    }
}
