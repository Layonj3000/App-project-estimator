package br.projeto.registro_proxy;

import br.projeto.adapter.IValidadorSenha;
import br.projeto.adapter.ValidadorSenhaAdapter;
import br.projeto.presenter.RegistroUsuarioPresenter;
import br.projeto.repository.UsuarioRepository;
import br.projeto.service.InstanciaRepositoryService;
import java.util.ArrayList;
import java.util.List;

public class RegistroProxy implements IRegistroProxy {
    private final RegistroUsuarioPresenter registroUsuarioPresenter;
    private final IValidadorSenha validador;
    private final UsuarioRepository usuarioRepository = InstanciaRepositoryService.getInstancia().getUsuarioRepository();
    
    public RegistroProxy(RegistroUsuarioPresenter registroUsuarioPresenter) {
        this.registroUsuarioPresenter = registroUsuarioPresenter;
        this.validador = new ValidadorSenhaAdapter();
    }

    @Override
    public void registrar() {
        try {
            if(validarUsuario()){
                registroUsuarioPresenter.registrar();
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    
    private boolean validarUsuario(){
        List<String> mensagensErro = new ArrayList<>();

        if (registroUsuarioPresenter.getNomeUsuario().isEmpty() || registroUsuarioPresenter.getEmail().isEmpty() || registroUsuarioPresenter.getSenha().length() == 0) {
            mensagensErro.add("Todos os campos devem ser preenchidos!");
        }

        if (registroUsuarioPresenter.getNomeUsuario().length() > 12) {
            mensagensErro.add("O nome deve ter no máximo 12 caracteres!");
        }

        if (!registroUsuarioPresenter.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            mensagensErro.add("Email inválido! Use o formato correto (exemplo@dominio.com).");
        }

        if (usuarioRepository.findByEmail(registroUsuarioPresenter.getEmail()) != null) {
            mensagensErro.add("Este email já está cadastrado! Tente outro.");
        }

        List<String> errosSenha = validador.validar(registroUsuarioPresenter.getSenha());
        mensagensErro.addAll(errosSenha);

        if (!mensagensErro.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", mensagensErro));
        }

        return true;
    }
}