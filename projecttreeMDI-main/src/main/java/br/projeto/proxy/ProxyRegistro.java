/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.proxy;

import com.pss.senha.validacao.ValidadorSenha;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author layon
 */
public class ProxyRegistro {
    private ValidadorSenha validador;

    public ProxyRegistro() {
        this.validador = new ValidadorSenha();
    }

    public boolean validarCampos(String nome, String email, String senha) {
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (nome.length() > 12) {
            JOptionPane.showMessageDialog(null, "O nome deve ter no máximo 12 caracteres!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(null, "Email inválido! Use o formato correto (exemplo@dominio.com).", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        List<String> resultado = validador.validar(senha);
        if (!resultado.isEmpty()) {
            String mensagem = String.join("\n", resultado);
            JOptionPane.showMessageDialog(null, mensagem, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}