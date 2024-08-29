package br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto;

import br.com.fiap.sistema.gestao.tarefas.model.tarefa.StatusTarefa;

public record AtualizarTarefaDTO(
        String titulo,
        String descricao) {
}
