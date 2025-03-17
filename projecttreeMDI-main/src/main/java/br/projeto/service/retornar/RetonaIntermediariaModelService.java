/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service.retornar;

import br.projeto.model.PerfilProjetoIntermediariaModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David Potentini
 */
public class RetonaIntermediariaModelService {
    private static RetonaIntermediariaModelService service = null;
    
    private RetonaIntermediariaModelService(){}
    
    public static RetonaIntermediariaModelService getInstancia(){
        if(service == null){
            service = new RetonaIntermediariaModelService();
        }
        return service;
    }
    
    public PerfilProjetoIntermediariaModel instantiatePerfilProjetoIntermediariaModel(ResultSet rs) throws SQLException {
        PerfilProjetoIntermediariaModel perfilProjetoIntermediariaModel = new PerfilProjetoIntermediariaModel(rs.getInt("projeto_id"), rs.getInt("perfil_id"));
        return perfilProjetoIntermediariaModel;
    }
}
