package br.com.fiap.sistema.gestao.tarefas.infrastructure.repository;

import br.com.fiap.sistema.gestao.tarefas.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
