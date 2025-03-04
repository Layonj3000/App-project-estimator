/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.model.UsuarioModel;
import br.projeto.presenter.TelaPresenter;
import br.projeto.repository.UsuarioRepository;
import br.projeto.repository_factory.RepositoryFactory;
import br.projeto.state.LoginState;
import br.projeto.view.TelaRegistro;
import com.pss.senha.validacao.ValidadorSenha;
import java.util.List;
import javax.swing.JOptionPane;



/**
 *
 * @author layon
 */
public class RegistroCommand implements Command{
    private String nome;
    private String email;
    private String senha;
    private ValidadorSenha validador;
    private TelaRegistro telaRegistro;

    public RegistroCommand(TelaRegistro telaRegistro,String nome, String email, String senha) {
        this.telaRegistro = telaRegistro;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.validador = new ValidadorSenha();
    }

    @Override
    public void execute() {
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nome.length() > 12) {
            JOptionPane.showMessageDialog(null, "O nome deve ter no máximo 12 caracteres!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(null, "Email inválido! Use o formato correto (exemplo@dominio.com).", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        List<String> resultado = validador.validar(senha);
        if(resultado.isEmpty()){
            RepositoryFactory factory1 = RepositoryFactory.escolherClasseFabricada(UsuarioRepository.class);
            UsuarioRepository usuarioRepository = factory1.createRepository();
            UsuarioModel novousuario = new UsuarioModel(5,nome, senha, email);
            usuarioRepository.insert(novousuario);
            JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!");
            telaRegistro.dispose();
            TelaPresenter presenter = new TelaPresenter(new LoginState());      
        }else {
            String mensagem = String.join("\n", resultado);
            JOptionPane.showMessageDialog(null, mensagem, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        }
    }
}
