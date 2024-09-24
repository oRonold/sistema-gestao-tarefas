package br.com.fiap.sistema.gestao.tarefas.domain.usuario;

import br.com.fiap.sistema.gestao.tarefas.domain.tarefa.Tarefa;
import br.com.fiap.sistema.gestao.tarefas.domain.usuario.dto.CriarUsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "GESTAO_USUARIO")
@Getter @Setter @NoArgsConstructor
@SequenceGenerator(name = "seq_gestao_usuario", sequenceName = "gestao_usuario_seq", allocationSize = 1)
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gestao_usuario")
    @Column(name = "cd_usuario")
    private Long id;
    @Column(name = "nm_usuario", nullable = false)
    private String nome;
    @Column(name = "ds_email", nullable = false, unique = true)
    private String email;
    @Column(name = "ds_senha", nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Tarefa> tarefas;

    public Usuario(CriarUsuarioDTO dto, String senha){
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
