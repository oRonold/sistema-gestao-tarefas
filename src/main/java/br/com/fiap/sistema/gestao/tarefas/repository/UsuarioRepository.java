package br.com.fiap.sistema.gestao.tarefas.repository;

import br.com.fiap.sistema.gestao.tarefas.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
