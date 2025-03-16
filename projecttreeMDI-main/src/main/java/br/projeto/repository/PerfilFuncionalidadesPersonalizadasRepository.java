/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.repository;

import br.projeto.db.DB;
import br.projeto.db.DbException;
import br.projeto.model.PerfilFuncionalidadesPersonalizadasModel;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.Subject;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.Observer;
import br.projeto.repository.abstr.IPerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.service.RetornaFuncionalidadesPersonalizadasModelService;
import br.projeto.service.RetornaPerfilModelService;
import br.projeto.service.RetornaUsuarioModelService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Potentini
 */
public class PerfilFuncionalidadesPersonalizadasRepository implements Subject, IPerfilFuncionalidadesPersonalizadasRepository{

    private Connection conn;
    private List<Observer> observers;
    private List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasModelList;
    private RetornaPerfilModelService perfilService;
    private RetornaUsuarioModelService serviceUsuario;
    private RetornaFuncionalidadesPersonalizadasModelService funcionalidadesPersonalizadasServicie;


    
    public PerfilFuncionalidadesPersonalizadasRepository(Connection conn) {
        this.conn = conn;
        observers = new ArrayList<>();
        perfilFuncionalidadesPersonalizadasModelList = new ArrayList<>(); 
        
        perfilService = new RetornaPerfilModelService();
        this.serviceUsuario = RetornaUsuarioModelService.getInstancia();
        this.funcionalidadesPersonalizadasServicie = RetornaFuncionalidadesPersonalizadasModelService.getInstancia();
    }
    
    

    @Override
    public List<PerfilFuncionalidadesPersonalizadasModel> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
            
        try {
            ps = conn.prepareStatement("SELECT perfil_funcionalidades_personalizadas.*, perfil_projeto_estimativa.* , usuario.nome, usuario.senha, usuario.email, usuario.formato_log " +
                                        "FROM perfil_funcionalidades_personalizadas " +
                                        "INNER JOIN perfil_projeto_estimativa ON perfil_funcionalidades_personalizadas.perfil_id = perfil_projeto_estimativa.id "+
                                        "INNER JOIN usuario ON perfil_projeto_estimativa.user_id = usuario.id "    
                                      );

            Map<Integer, PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelMap = new HashMap<>();
            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasModelList = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                PerfilProjetoDeEstimativaModel perfilDeEstimativa = perfilProjetoDeEstimativaModelMap.get(rs.getInt("perfil_id"));
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = serviceUsuario.instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }                
                if (perfilDeEstimativa == null) {
                    perfilDeEstimativa = perfilService.instantiatePerfilComResultSet(rs, usuario);
                    perfilProjetoDeEstimativaModelMap.put(perfilDeEstimativa.getId(), perfilDeEstimativa);
                }
                PerfilFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = funcionalidadesPersonalizadasServicie.instantiatePerfilFuncionalidadesPersonalizadasModel(rs, perfilDeEstimativa);
                perfilFuncionalidadesPersonalizadasModelList.add(projetosFuncionalidadesPersonalizadasModel);
            }
            return perfilFuncionalidadesPersonalizadasModelList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public PerfilFuncionalidadesPersonalizadasModel findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT perfil_funcionalidades_personalizadas.*, perfil_projeto_estimativa.* , usuario.nome, usuario.senha, usuario.email, usuario.formato_log " +
                                       "FROM perfil_funcionalidades_personalizadas "+ 
                                        "INNER JOIN perfil_projeto_estimativa ON perfil_funcionalidades_personalizadas.perfil_id = perfil_projeto_estimativa.id "+
                                        "INNER JOIN usuario ON perfil_projeto_estimativa.user_id = usuario.id "+  
                                        "WHERE perfil_funcionalidades_personalizadas.id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioModel usuarioModel = serviceUsuario.instantiateUsuarioModel(rs);
                PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel = perfilService.instantiatePerfilComResultSet(rs, usuarioModel);
                PerfilFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = funcionalidadesPersonalizadasServicie.instantiatePerfilFuncionalidadesPersonalizadasModel(rs, perfilProjetoDeEstimativaModel);
                return projetosFuncionalidadesPersonalizadasModel;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public List<PerfilFuncionalidadesPersonalizadasModel> findByPerfilProjetoEstimativa(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT perfil_funcionalidades_personalizadas.*, perfil_projeto_estimativa.* , usuario.nome, usuario.senha, usuario.email, usuario.formato_log " +
                                        "FROM perfil_funcionalidades_personalizadas " +
                                        "INNER JOIN perfil_projeto_estimativa ON perfil_funcionalidades_personalizadas.perfil_id = perfil_projeto_estimativa.id "+
                                        "INNER JOIN usuario ON perfil_projeto_estimativa.user_id = usuario.id "+
                                        "WHERE perfil_id = ?"    
                                      );
            
            
            ps.setInt(1, perfilProjetoDeEstimativaModel.getId());

            Map<Integer, PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelMap = new HashMap<>();
            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<PerfilFuncionalidadesPersonalizadasModel> perfilFuncionalidadesPersonalizadasModelList = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                PerfilProjetoDeEstimativaModel perfilDeEstimativa = perfilProjetoDeEstimativaModelMap.get(rs.getInt("perfil_id"));
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = serviceUsuario.instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }                
                if (perfilDeEstimativa == null) {
                    perfilDeEstimativa = perfilService.instantiatePerfilComResultSet(rs, usuario);
                    perfilProjetoDeEstimativaModelMap.put(perfilDeEstimativa.getId(), perfilDeEstimativa);
                }
                PerfilFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = funcionalidadesPersonalizadasServicie.instantiatePerfilFuncionalidadesPersonalizadasModel(rs, perfilDeEstimativa);
                perfilFuncionalidadesPersonalizadasModelList.add(projetosFuncionalidadesPersonalizadasModel);
            }
            return perfilFuncionalidadesPersonalizadasModelList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }   
    }

    @Override
    public void insert(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel) {
                PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("INSERT INTO perfil_funcionalidades_personalizadas (nome, valor, perfil_id) "+
                                       "VALUES (?,?,?)"
                                       , PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, perfilFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, perfilFuncionalidadesPersonalizadasModel.getValor());
            ps.setInt(3, perfilFuncionalidadesPersonalizadasModel.getIdPerfilProjetoDeEstimativaModel());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    perfilFuncionalidadesPersonalizadasModel.setId(rs.getInt(1));
                    perfilFuncionalidadesPersonalizadasModelList.add(perfilFuncionalidadesPersonalizadasModel);
                    notifyObservers();
                } else {
                    throw new DbException("Unexpected error! No rows affected!");
                }
            }
        } catch (SQLException | NullPointerException e) {
            throw new DbException(e.getMessage());
        }finally {
                DB.closeStatement(ps);
                DB.closeResultSet(rs);    
        }    
    }

    @Override
    public void update(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE perfil_funcionalidades_personalizadas "+
                    "SET nome = ?, valor = ?"+
                    "WHERE id = ?"        
            );
            ps.setString(1, perfilFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, perfilFuncionalidadesPersonalizadasModel.getValor());
            ps.setInt(3, perfilFuncionalidadesPersonalizadasModel.getId());
            
            ps.executeUpdate();
            
        perfilFuncionalidadesPersonalizadasModelList.add(perfilFuncionalidadesPersonalizadasModel);
        notifyObservers();
        } catch (SQLException | NullPointerException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }    
    }
    
    @Override
    public void updateByPerfilProjetoDeEstimativa(PerfilFuncionalidadesPersonalizadasModel perfilFuncionalidadesPersonalizadasModel, PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE perfil_funcionalidades_personalizadas "+
                    "SET nome = ?, valor = ?"+
                    "WHERE perfil_id  = ?"        
            );
            ps.setString(1, perfilFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, perfilFuncionalidadesPersonalizadasModel.getValor());
            ps.setInt(3, perfilProjetoDeEstimativaModel.getId());
            
            ps.executeUpdate();
            
        perfilFuncionalidadesPersonalizadasModelList.add(perfilFuncionalidadesPersonalizadasModel);
        notifyObservers();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM perfil_funcionalidades_personalizadas WHERE id=?");
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                perfilFuncionalidadesPersonalizadasModelList.removeIf(item -> item.getId().equals(id));
                notifyObservers();
                return true;
            }else{
                throw new DbException("Unexpected error! No rows affected!");
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }    
    }
    
    @Override
    public boolean deleteByPerfilProjetoDeEstimativa(PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM perfil_funcionalidades_personalizadas WHERE perfil_id=?");
            ps.setInt(1, perfilProjetoDeEstimativaModel.getId());
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                perfilFuncionalidadesPersonalizadasModelList.removeIf(item -> item.getIdPerfilProjetoDeEstimativaModel().equals(perfilProjetoDeEstimativaModel.getId()));
                notifyObservers();
                return true;
            }else{
                throw new DbException("Unexpected error! No rows affected!");
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }    
    }
    


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);   
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.updatePerfilFuncionalidadesPersonalizadasModel(perfilFuncionalidadesPersonalizadasModelList);
        }    
    }
    
}
