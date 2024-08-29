package br.com.fiap.sistema.gestao.tarefas.repository;

import br.com.fiap.sistema.gestao.tarefas.model.tarefa.Tarefa;
import br.com.fiap.sistema.gestao.tarefas.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findAllByUsuario(Usuario usuario);

    Tarefa findByIdAndUsuario(Long id, Usuario usuario);
}
