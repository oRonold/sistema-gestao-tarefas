package br.com.fiap.sistema.gestao.tarefas.infrastructure.repository;

import br.com.fiap.sistema.gestao.tarefas.domain.tarefa.Tarefa;
import br.com.fiap.sistema.gestao.tarefas.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findAllByUsuario(Usuario usuario);

    Tarefa findByIdAndUsuario(Long id, Usuario usuario);
}
