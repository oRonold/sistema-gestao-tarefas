package br.com.fiap.sistema.gestao.tarefas.model.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUsuarioDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha) {
}
