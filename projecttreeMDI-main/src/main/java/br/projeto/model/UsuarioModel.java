package br.projeto.model;

import br.projeto.enums.FormatoLOG;

public class UsuarioModel {
    private Integer id;
    private String nome;
    private String senha;
    private String email;
    private String formatoLOG;

    public UsuarioModel(Integer id, String nome, String senha, String email, String formatoLOG) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.formatoLOG = formatoLOG;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFormatoLOG() {
        return formatoLOG;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" + "id=" + id + 
                ", nome=" + nome + 
                ", senha=" + senha + 
                ", email=" + email + 
                ", formatoLOG=" + formatoLOG + '}';
    }


}
