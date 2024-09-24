package br.com.fiap.sistema.gestao.tarefas.domain.tarefa.dto;

import jakarta.validation.constraints.NotBlank;

public record CriarTarefaDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao) {
}
