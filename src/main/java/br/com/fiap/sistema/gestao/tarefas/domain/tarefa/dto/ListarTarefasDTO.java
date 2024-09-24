package br.com.fiap.sistema.gestao.tarefas.domain.tarefa.dto;

import br.com.fiap.sistema.gestao.tarefas.domain.tarefa.StatusTarefa;
import br.com.fiap.sistema.gestao.tarefas.domain.tarefa.Tarefa;

public record ListarTarefasDTO(String titulo, StatusTarefa status) {

    public ListarTarefasDTO(Tarefa tarefa){
        this(tarefa.getTitulo(), tarefa.getStatusTarefa());
    }

}
