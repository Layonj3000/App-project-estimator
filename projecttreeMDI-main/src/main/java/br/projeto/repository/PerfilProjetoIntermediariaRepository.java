package br.projeto.repository;

import br.projeto.db.DB;
import br.projeto.repository.abstr.IPerfilProjetoIntermediariaRepository;
import br.projeto.db.DbException;
import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.PerfilProjetoIntermediariaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.Subject;
import br.projeto.presenter.Observer;
import br.projeto.service.RetonaIntermediariaModelService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PerfilProjetoIntermediariaRepository implements IPerfilProjetoIntermediariaRepository, Subject{

    private Connection conn;
    private List<Observer> observers;
    private List<PerfilProjetoIntermediariaModel> perfilProjetoIntermediariaModelList;
    
    private RetonaIntermediariaModelService intermediariaService;

    public PerfilProjetoIntermediariaRepository(Connection conn) {
        this.conn = conn;       
        observers = new ArrayList<>();
        perfilProjetoIntermediariaModelList = new ArrayList<>();
        
        this.intermediariaService = RetonaIntermediariaModelService.getInstancia();
    }
    

    @Override
    public List<PerfilProjetoIntermediariaModel> findAll() {
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM perfil_projeto_intermediaria");
            List<PerfilProjetoIntermediariaModel> perfilProjetoIntermediariaModelList = new ArrayList<>();
            while (rs.next()) {
                PerfilProjetoIntermediariaModel perfilProjetoIntermediariaModel = intermediariaService.instantiatePerfilProjetoIntermediariaModel(rs);
                perfilProjetoIntermediariaModelList.add(perfilProjetoIntermediariaModel);
            }
            return perfilProjetoIntermediariaModelList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public PerfilProjetoIntermediariaModel findById(Integer idProjeto, Integer idPerfil) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM perfil_projeto_intermediaria "
                    + "WHERE projeto_id = ? "
                    + "AND perfil_id = ?");
            ps.setInt(1, idProjeto);
            ps.setInt(2, idPerfil);

            rs = ps.executeQuery();
            if (rs.next()) {
                return intermediariaService.instantiatePerfilProjetoIntermediariaModel(rs);
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
    public List<PerfilProjetoIntermediariaModel> findByProjeto(Integer idProjeto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM perfil_projeto_intermediaria "
                    + "WHERE projeto_id = ? ");
            ps.setInt(1, idProjeto);

            rs = ps.executeQuery();
            List<PerfilProjetoIntermediariaModel> perfilProjetoIntermediariaModelList = new ArrayList<>();
        while (rs.next()) {
                PerfilProjetoIntermediariaModel perfilProjetoIntermediariaModel = intermediariaService.instantiatePerfilProjetoIntermediariaModel(rs);
                perfilProjetoIntermediariaModelList.add(perfilProjetoIntermediariaModel);
        }
            return perfilProjetoIntermediariaModelList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void insert(ProjetoDeEstimativaModel projetoDeEstimativaModel, PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO perfil_projeto_intermediaria(projeto_id, perfil_id) "
                    + "VALUES(?, ?)");
            ps.setInt(1, projetoDeEstimativaModel.getId());
            ps.setInt(2, perfilProjetoDeEstimativaModel.getId());
            ps.executeUpdate();
            
            
            perfilProjetoIntermediariaModelList.add(new PerfilProjetoIntermediariaModel(projetoDeEstimativaModel.getId(),perfilProjetoDeEstimativaModel.getId()));
            notifyObservers();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void insertMutiple(ProjetoDeEstimativaModel projetoDeEstimativaModel, List<PerfilProjetoDeEstimativaModel> perfilProjetoDeEstimativaModelList) {
        PreparedStatement ps = null;
        try {
            for (PerfilProjetoDeEstimativaModel perfilProjetoDeEstimativaModel : perfilProjetoDeEstimativaModelList) {
                ps = conn.prepareStatement("INSERT INTO perfil_projeto_intermediaria "
                        + "VALUES (?, ?)");
                ps.setInt(1, projetoDeEstimativaModel.getId());
                ps.setInt(2, perfilProjetoDeEstimativaModel.getId());

                ps.executeUpdate();
                
                perfilProjetoIntermediariaModelList.add(new PerfilProjetoIntermediariaModel(projetoDeEstimativaModel.getId(),perfilProjetoDeEstimativaModel.getId()));
                notifyObservers();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer idProjeto, Integer idPerfil) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM perfil_projeto_intermediaria "
                    + "WHERE projeto_id = ? "
                    + "AND perfil_id = ? ");
            ps.setInt(1, idProjeto);
            ps.setInt(2, idPerfil);

            ps.executeUpdate();
            
            perfilProjetoIntermediariaModelList.removeIf(item -> item.getIdProjetoDeEstimativaModel().equals(idPerfil) && item.getIdPerfilProjetoDeEstimativaModel().equals(idPerfil));
            notifyObservers();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }
    @Override
    public void deleteByProjeto(Integer idProjeto) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM perfil_projeto_intermediaria "
                    + "WHERE projeto_id = ?");
            ps.setInt(1, idProjeto);

            ps.executeUpdate();
            
            perfilProjetoIntermediariaModelList.removeIf(item -> item.getIdProjetoDeEstimativaModel().equals(idProjeto));
            notifyObservers();
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
        for(Observer observer:observers){
            observer.updatePerfilProjetoIntermediariaModel(perfilProjetoIntermediariaModelList);
            }   
        }
    }



