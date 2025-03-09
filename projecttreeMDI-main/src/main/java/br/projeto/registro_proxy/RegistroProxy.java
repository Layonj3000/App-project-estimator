/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.registro_proxy;

import br.projeto.presenter.LoginUsuarioPresenter;
import br.projeto.presenter.RegistroUsuarioPresenter;
import com.pss.senha.validacao.ValidadorSenha;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author layon
 */
public class RegistroProxy implements IRegistroProxy{
    private RegistroUsuarioPresenter registroUsuarioPresenter;
    private ValidadorSenha validador;
    public RegistroProxy(RegistroUsuarioPresenter registroUsuarioPresenter) {
        this.registroUsuarioPresenter =  registroUsuarioPresenter;
        this.validador = new ValidadorSenha();
    }

    @Override
    public void registrar() {
        if(validarUsuario()){
            registroUsuarioPresenter.registrar();
            new LoginUsuarioPresenter();
            registroUsuarioPresenter.getView().dispose();
        }
    }
    
    private boolean validarUsuario(){
        List<String> mensagensErro = new ArrayList<>();

        if (registroUsuarioPresenter.getNomeUsuario().isEmpty()|| registroUsuarioPresenter.getEmail().isEmpty() ||  registroUsuarioPresenter.getSenha().length() == 0) {
            mensagensErro.add("Todos os campos devem ser preenchidos!");
        }

        if (registroUsuarioPresenter.getNomeUsuario().length() > 12) {
            mensagensErro.add("O nome deve ter no máximo 12 caracteres!");
        }

        if (!registroUsuarioPresenter.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            mensagensErro.add("Email inválido! Use o formato correto (exemplo@dominio.com).");
        }

        List<String> errosSenha = validador.validar(registroUsuarioPresenter.getSenha());
        mensagensErro.addAll(errosSenha);

        // Se houver erros, exibe todos em um único JOptionPane
        if (!mensagensErro.isEmpty()) {
            JOptionPane.showMessageDialog(null, String.join("\n", mensagensErro), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
        
    }
    
}
