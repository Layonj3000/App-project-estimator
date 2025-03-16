/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.perfil_builder;

import br.projeto.model.UsuarioModel;
import br.projeto.presenter.Observer;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.UsuarioRepository;
import br.projeto.service.InstanciaRepositoryService;



/**
 *
 * @author David Potentini
 */
public class BuilderObserver extends Observer{
    private final Diretor diretor;
    private final PerfilProjetoDeEstimativaRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private InstanciaRepositoryService repositoryService = InstanciaRepositoryService.getInstancia();
    
    

    public BuilderObserver(UsuarioRepository usuarioRepository){
        this.diretor = new Diretor();
        this.perfilRepository = repositoryService.getPerfilProjetoDeEstimativaRepository();
        this.usuarioRepository = usuarioRepository;
        
        usuarioRepository.addObserver(this);
    }
    
    private void registrarPerfisPadrao(UsuarioModel usuarioModel) {
        perfilRepository.insert(diretor.criar(new BackEndBuilder(usuarioModel)));
        perfilRepository.insert(diretor.criar(new AndroidBuilder(usuarioModel)));
        perfilRepository.insert(diretor.criar(new IosBuilder(usuarioModel)));
    }
    
    @Override
    public void updateUsuarioModel(UsuarioModel usuarioModel){
        registrarPerfisPadrao(usuarioModel);
    }


    
}
