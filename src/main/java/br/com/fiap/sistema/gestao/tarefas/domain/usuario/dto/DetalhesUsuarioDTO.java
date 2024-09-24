package br.com.fiap.sistema.gestao.tarefas.domain.usuario.dto;

import br.com.fiap.sistema.gestao.tarefas.domain.usuario.Usuario;

public record DetalhesUsuarioDTO(String nome, String email, String senha) {

    public DetalhesUsuarioDTO(Usuario usuario) {
        this(usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}
