/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.adapter.validadorsenha;

import com.pss.senha.validacao.ValidadorSenha;
import java.util.List;

/**
 *
 * @author layon
 */
public class ValidadorSenhaAdapter implements IValidadorSenha {
    private ValidadorSenha validador;

    public ValidadorSenhaAdapter() {
        this.validador = new ValidadorSenha();
    }

    @Override
    public List<String> validar(String senha) {
        return validador.validar(senha);
    }
    
}
