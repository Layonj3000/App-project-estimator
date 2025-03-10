/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.repository.PerfilFuncionalidadesPersonalizadasRepository;
import br.projeto.repository.PerfilProjetoDeEstimativaRepository;
import br.projeto.repository.PerfilProjetoIntermediariaRepository;
import br.projeto.repository.ProjetoDeEstimativaRepository;
import br.projeto.repository.ProjetoFuncionalidadesPersonalizadasRepository;

/**
 *
 * @author layon
 */
public class IniciarTelaCompartilharCommand implements Command{
    private final ProjetoDeEstimativaRepository projetoDeEstimativaRepository;//NOVO
    private final ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository;//NOVO
    private final PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository;
    private final UsuarioModel usuarioModel;
    private Integer projetoId;

    public IniciarTelaCompartilharCommand(ProjetoDeEstimativaRepository projetoDeEstimativaRepository, ProjetoFuncionalidadesPersonalizadasRepository projetoFuncionalidadesPersonalizadasRepository, PerfilProjetoIntermediariaRepository perfilProjetoIntermediariaRepository, UsuarioModel usuarioModel) {
        this.projetoDeEstimativaRepository = projetoDeEstimativaRepository;
        this.projetoFuncionalidadesPersonalizadasRepository = projetoFuncionalidadesPersonalizadasRepository;
        this.perfilProjetoIntermediariaRepository = perfilProjetoIntermediariaRepository;
        this.usuarioModel = usuarioModel;
    }

    public void setProjetoId(Integer projetoId) {
        this.projetoId = projetoId;
    }
    
    @Override
    public void execute() {
        //chama a presenter
    }
    
}
