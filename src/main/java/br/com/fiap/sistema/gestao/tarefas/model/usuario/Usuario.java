package br.com.fiap.sistema.gestao.tarefas.model.usuario;

import br.com.fiap.sistema.gestao.tarefas.model.usuario.dto.CriarUsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GESTAO_USUARIO")
@Getter @Setter @NoArgsConstructor
@SequenceGenerator(name = "seq_gestao_usuario", sequenceName = "gestao_usuario_seq")
public class Usuario {

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

    public Usuario(CriarUsuarioDTO dto, String senha){
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = senha;
    }
}
