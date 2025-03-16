/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.service;

import br.projeto.model.UsuarioModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David Potentini
 */
public class RetornaUsuarioModelService {
    private static RetornaUsuarioModelService service = null;
    
    private RetornaUsuarioModelService(){}
    
    public static RetornaUsuarioModelService getInstancia(){
        if(service == null){
            service = new RetornaUsuarioModelService();
        }
        return service;
    }
    
    public UsuarioModel instantiateUsuarioModel(ResultSet rs) throws SQLException {
        UsuarioModel usuarioModel = new UsuarioModel(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"), rs.getString("email"), rs.getString("formato_log"));
        return usuarioModel;
    }
}
