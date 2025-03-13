/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.PerfilProjetoDeEstimativaModel;
import br.projeto.model.PerfilProjetoIntermediariaModel;
import br.projeto.model.ProjetoDeEstimativaModel;
import br.projeto.model.ProjetosFuncionalidadesPersonalizadasModel;
import br.projeto.model.UsuarioModel;
import br.projeto.presenter.CompartilharPresenter;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.UsuarioRepository;
import br.projeto.service.InstanciaRepositoryService;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author layon
 */
public class CompartilharCommand implements Command{
    private final CompartilharPresenter compartilharPresenter;
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;
    private final UsuarioModel usuarioModel;
    private final Integer projetoID;
    private final UsuarioRepository usuarioRepository = InstanciaRepositoryService.getInstancia().getUsuarioRepository();
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository;
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;
    
    public CompartilharCommand(CompartilharPresenter compartilharPresenter, PerfilProjetoDeEstimativaRepository perfilProjetoDeEstimativaRepository,ProjetoDeEstimativaRepository projetoDeEstimativaRepository,ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository, PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel, Integer projetoID) {
        this.compartilharPresenter = compartilharPresenter;
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.perfilProjetoDeEstimativaRepository = perfilProjetoDeEstimativaRepository;
        this.usuarioModel = usuarioModel;
        this.perfilProjetoIntermediariaRepository =  perfilProjetoIntermediariaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.projetoID = projetoID;
    }


    @Override
    public void execute() {

        if (compartilharPresenter.getEmail().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email não pode ser nulo ou vazio", "Erro", JOptionPane.ERROR_MESSAGE);
            return;        
       }
        if (usuarioRepository.findByEmail(compartilharPresenter.getEmail()) == null) {
            JOptionPane.showMessageDialog(null, "Email não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            return;        
       }
        if (compartilharPresenter.getEmail().equals(usuarioModel.getEmail())) {
            JOptionPane.showMessageDialog(null, "Você não pode compartilhar consigo mesmo", "Erro", JOptionPane.ERROR_MESSAGE);
            return;        
       }        
        
        ProjetoDeEstimativaModel projeto = projetoDeEstimativaRepository.findById(projetoID);
        if(projeto.getCompartilhadoValor() == 1){
            JOptionPane.showMessageDialog(null, "Não é possível compartilhar um projeto compartilhado", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        List<PerfilProjetoDeEstimativaModel> perfis = perfilProjetoDeEstimativaRepository.findByProjetoEstimativa(projeto);
        List<ProjetosFuncionalidadesPersonalizadasModel> funcionalidadesPersonalizadas = projetoFuncionalidadesPersonalizadasRepository.findByProjetoEstimativa(projeto);
        
        projeto.setCompartilhado(1); 
        projeto.setCompartilhadoPor(usuarioModel.getId());
        projeto.setUsuarioModel(usuarioRepository.findByEmail(compartilharPresenter.getEmail()));
        projeto.setId(null);  // Definindo o ID como nulo para criar um novo projeto
        projetoDeEstimativaRepository.insert(projeto);
        
        List<PerfilProjetoIntermediariaModel> perfisProjeto = perfilProjetoIntermediariaRepository.findByProjeto(projetoID);
//        
//        if(!perfilProjetoIntermediariaRepository.findByProjeto(idProjetoCompartilhado).isEmpty()){
//            perfilProjetoIntermediariaRepository.deleteById(idProjetoCompartilhado, perfis);
//        }        

        for (PerfilProjetoDeEstimativaModel perfil : perfis) {   
            perfilProjetoIntermediariaRepository.insert(projeto, perfil);
        }

       for (ProjetosFuncionalidadesPersonalizadasModel funcionalidade : funcionalidadesPersonalizadas) {
           funcionalidade.setProjetoDeEstimativaModel(projeto);
           projetoFuncionalidadesPersonalizadasRepository.insert(funcionalidade);
        }        

//        System.out.println("Projeto compartilhado com sucesso!");
    }
}
