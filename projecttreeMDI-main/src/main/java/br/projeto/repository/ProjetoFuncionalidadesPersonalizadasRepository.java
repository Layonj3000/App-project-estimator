/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.repository;

import br.projeto.db.DB;
import br.projeto.db.DbException;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import br.projeto.model.Subject;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.Observer;
import br.projeto.repository.abstr.IProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.service.RetornaProjetoModelService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ProjetoFuncionalidadesPersonalizadasRepository implements Subject, IProjetoFuncionalidadesPersonalizadasRepository{
    private Connection conn;
    private List<Observer> observers;
    private List<ProjetosFuncionalidadesPersonalizadasModel> projetosFuncionalidadesPersonalizadasModelList;
    
    private RetornaProjetoModelService serviceProjeto;
    
    public ProjetoFuncionalidadesPersonalizadasRepository(Connection conn) {
        this.conn = conn;
        observers = new ArrayList<>();
        projetosFuncionalidadesPersonalizadasModelList = new ArrayList<>();
        
        serviceProjeto = new RetornaProjetoModelService();        
    }
    
    

    @Override
    public List<ProjetosFuncionalidadesPersonalizadasModel> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT projeto_funcionalidades_personalizadas.*, projetos_estimativa.* , usuario.nome, usuario.senha, usuario.email, usuario.formato_log " +
                                        "FROM projeto_funcionalidades_personalizadas " +
                                        "INNER JOIN projetos_estimativa ON projeto_funcionalidades_personalizadas.projeto_id = projetos_estimativa.id "+
                                        "INNER JOIN usuario ON projetos_estimativa.user_id = usuario.id "    
                                      );

            Map<Integer, ProjetoDeEstimativaModel> projetoDeEstimativaModelMap = new HashMap<>();
            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<ProjetosFuncionalidadesPersonalizadasModel> projetosFuncionalidadesPersonalizadasModelListMetodo = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                ProjetoDeEstimativaModel projetoDeEstimativa = projetoDeEstimativaModelMap.get(rs.getInt("projeto_id"));
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }                
                if (projetoDeEstimativa == null) {
                    projetoDeEstimativa = serviceProjeto.instantiateProjetoDeEstimativaModel(rs, usuario);
                    projetoDeEstimativaModelMap.put(projetoDeEstimativa.getId(), projetoDeEstimativa);
                }
                ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = instantiateProjetosFuncionalidadesPersonalizadasModel(rs, projetoDeEstimativa);
                projetosFuncionalidadesPersonalizadasModelListMetodo.add(projetosFuncionalidadesPersonalizadasModel);
            }
            return projetosFuncionalidadesPersonalizadasModelListMetodo;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public ProjetosFuncionalidadesPersonalizadasModel findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT projeto_funcionalidades_personalizadas.*, projetos_estimativa.* , usuario.nome, usuario.senha, usuario.email, usuario.formato_log " +
                                       "FROM projeto_funcionalidades_personalizadas "+ 
                                        "INNER JOIN projetos_estimativa ON projeto_funcionalidades_personalizadas.projeto_id = projetos_estimativa.id "+
                                        "INNER JOIN usuario ON projetos_estimativa.user_id = usuario.id "+  
                                        "WHERE projeto_funcionalidades_personalizadas.id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioModel usuarioModel = instantiateUsuarioModel(rs);
                ProjetoDeEstimativaModel projetoDeEstimativaModel = serviceProjeto.instantiateProjetoDeEstimativaModel(rs, usuarioModel);
                ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = instantiateProjetosFuncionalidadesPersonalizadasModel(rs, projetoDeEstimativaModel);
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
    public List<ProjetosFuncionalidadesPersonalizadasModel> findByProjetoEstimativa(ProjetoDeEstimativaModel projetoDeEstimativaModel) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT projeto_funcionalidades_personalizadas.*, projetos_estimativa.* , usuario.nome, usuario.senha, usuario.email, usuario.formato_log " +
                                        "FROM projeto_funcionalidades_personalizadas " +
                                        "INNER JOIN projetos_estimativa ON projeto_funcionalidades_personalizadas.projeto_id = projetos_estimativa.id "+
                                        "INNER JOIN usuario ON projetos_estimativa.user_id = usuario.id "+
                                        "WHERE projeto_id = ?"    
                                      );
            
            ps.setInt(1, projetoDeEstimativaModel.getId());

            Map<Integer, ProjetoDeEstimativaModel> projetoDeEstimativaModelMap = new HashMap<>();
            Map<Integer, UsuarioModel> usuarioModelMap = new HashMap<>();
            List<ProjetosFuncionalidadesPersonalizadasModel> projetosFuncionalidadesPersonalizadasModelListMetodo = new ArrayList<>();
            rs = ps.executeQuery();

            while (rs.next()) {
                ProjetoDeEstimativaModel projetoDeEstimativa = projetoDeEstimativaModelMap.get(rs.getInt("projeto_id"));
                UsuarioModel usuario = usuarioModelMap.get(rs.getInt("user_id"));
                if (usuario == null) {
                    usuario = instantiateUsuarioModel(rs);
                    usuarioModelMap.put(usuario.getId(), usuario);
                }                
                if (projetoDeEstimativa == null) {
                    projetoDeEstimativa = serviceProjeto.instantiateProjetoDeEstimativaModel(rs, usuario);
                    projetoDeEstimativaModelMap.put(projetoDeEstimativa.getId(), projetoDeEstimativa);
                }
                ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadas = instantiateProjetosFuncionalidadesPersonalizadasModel(rs, projetoDeEstimativa);
                projetosFuncionalidadesPersonalizadasModelListMetodo.add(projetosFuncionalidadesPersonalizadas);
            }
            return projetosFuncionalidadesPersonalizadasModelListMetodo;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }        
    }

    @Override
    public void insert(ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel) {
                PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("INSERT INTO projeto_funcionalidades_personalizadas (nome, selecionado, projeto_id) "+
                                       "VALUES (?,?,?)"
                                       , PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, projetosFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, projetosFuncionalidadesPersonalizadasModel.getSelecionadoValor());
            ps.setInt(3, projetosFuncionalidadesPersonalizadasModel.getIdProjetoDeEstimativa());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    projetosFuncionalidadesPersonalizadasModel.setId(rs.getInt(1));
                    projetosFuncionalidadesPersonalizadasModelList.add(projetosFuncionalidadesPersonalizadasModel);
                    notifyObservers();
                    //return rs.getInt(1);
                } else {
                    throw new DbException("Unexpected error! No rows affected!");
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        //return null;
    }

    @Override
    public void update(ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE projeto_funcionalidades_personalizadas "+
                    "SET nome = ?, selecionado = ?"+
                    "WHERE id = ?"        
            );
            ps.setString(1, projetosFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, projetosFuncionalidadesPersonalizadasModel.getSelecionadoValor());
            ps.setInt(3, projetosFuncionalidadesPersonalizadasModel.getId());
            
            ps.executeUpdate();
            
        projetosFuncionalidadesPersonalizadasModelList.add(projetosFuncionalidadesPersonalizadasModel);
        notifyObservers();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }    
    }
    
    @Override
    public void updateByProjetoDeEstimativa(ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel,ProjetoDeEstimativaModel projetoDeEstimativaModel){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                    "UPDATE projeto_funcionalidades_personalizadas "+
                    "SET nome = ?, selecionado = ?"+
                    "WHERE projeto_id = ?"        
            );
            ps.setString(1, projetosFuncionalidadesPersonalizadasModel.getNome());
            ps.setInt(2, projetosFuncionalidadesPersonalizadasModel.getSelecionadoValor());
            ps.setInt(3, projetoDeEstimativaModel.getId());
            
            ps.executeUpdate();
            
        projetosFuncionalidadesPersonalizadasModelList.add(projetosFuncionalidadesPersonalizadasModel);
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
            ps = conn.prepareStatement("DELETE FROM projeto_funcionalidades_personalizadas WHERE id=?");
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                projetosFuncionalidadesPersonalizadasModelList.removeIf(item -> item.getId().equals(id));
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
    public boolean deleteByProjetoDeEstimativa(ProjetoDeEstimativaModel projetoDeEstimativaModel) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM projeto_funcionalidades_personalizadas WHERE projeto_id=?");
            ps.setInt(1, projetoDeEstimativaModel.getId());
            int rowsAffected = ps.executeUpdate();
            
            if(rowsAffected > 0){
                projetosFuncionalidadesPersonalizadasModelList.removeIf(item -> item.getIdProjetoDeEstimativa().equals(projetoDeEstimativaModel.getId()));
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
    
    
    
    private ProjetosFuncionalidadesPersonalizadasModel instantiateProjetosFuncionalidadesPersonalizadasModel(ResultSet rs, ProjetoDeEstimativaModel projetoDeEstimativaModel) throws SQLException {
        ProjetosFuncionalidadesPersonalizadasModel projetosFuncionalidadesPersonalizadasModel = new ProjetosFuncionalidadesPersonalizadasModel();
        projetosFuncionalidadesPersonalizadasModel.setId(rs.getInt("id"));
        projetosFuncionalidadesPersonalizadasModel.setNome(rs.getString("nome"));
        projetosFuncionalidadesPersonalizadasModel.setSelecionado((rs.getInt("selecionado")));
        projetosFuncionalidadesPersonalizadasModel.setProjetoDeEstimativaModel(projetoDeEstimativaModel);
        
        return projetosFuncionalidadesPersonalizadasModel;
    }


    private UsuarioModel instantiateUsuarioModel(ResultSet rs) throws SQLException {
        UsuarioModel usuarioModel = new UsuarioModel(rs.getInt("user_id"), rs.getString("nome"), rs.getString("senha"), rs.getString("email"), rs.getString("formato_log"));
        return usuarioModel;    }

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
            observer.updateProjetoFuncionalidadesPersonalizadasModel(projetosFuncionalidadesPersonalizadasModelList);
        }    
    }


    
}
