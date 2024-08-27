package br.com.fiap.sistema.gestao.tarefas.repository;

import br.com.fiap.sistema.gestao.tarefas.model.tarefa.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
