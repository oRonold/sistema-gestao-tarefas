package br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto;

import br.com.fiap.sistema.gestao.tarefas.model.tarefa.StatusTarefa;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.Tarefa;

public record ListarTarefasDTO(String titulo, StatusTarefa status) {

    public ListarTarefasDTO(Tarefa tarefa){
        this(tarefa.getTitulo(), tarefa.getStatusTarefa());
    }

}
