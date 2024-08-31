package br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto;

import br.com.fiap.sistema.gestao.tarefas.model.tarefa.StatusTarefa;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.Tarefa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DetalhesTarefaDTO(
        Long id,
        String titulo,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataPublicacao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataFinalizacao,
        StatusTarefa status) {

    public DetalhesTarefaDTO(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataCriacao(), tarefa.getDataFinalizacao(), tarefa.getStatusTarefa());
    }
}
