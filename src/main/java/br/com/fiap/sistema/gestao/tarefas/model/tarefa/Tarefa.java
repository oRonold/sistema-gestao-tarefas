package br.com.fiap.sistema.gestao.tarefas.model.tarefa;

import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.AtualizarTarefaDTO;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.CriarTarefaDTO;
import br.com.fiap.sistema.gestao.tarefas.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "GESTAO_TAREFA")
@Getter @Setter @NoArgsConstructor
@SequenceGenerator(name = "seq_gestao_tarefa", sequenceName = "gestao_tarefa_seq", allocationSize = 1)
@EntityListeners(AuditingEntityListener.class)
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gestao_tarefa")
    @Column(name = "cd_tarefa")
    private Long id;

    @Column(name = "ds_titulo", nullable = false)
    private String titulo;

    @Column(name = "dsc_tarefa", nullable = false)
    private String descricao;

    @Column(name = "dt_criacao", nullable = false)
    @CreatedDate
    private LocalDate dataCriacao;

    @Column(name = "dt_finalizacao")
    private LocalDate dataFinalizacao;

    @Column(name = "st_tarefa", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTarefa statusTarefa;

    @ManyToOne
    @JoinColumn(name = "cd_usuario", nullable = false)
    private Usuario usuario;

    public Tarefa(CriarTarefaDTO dto){
        this.titulo = dto.titulo();
        this.descricao = dto.descricao();
        this.statusTarefa = StatusTarefa.PENDENTE;
    }

    public void atualizar(AtualizarTarefaDTO dto){
        if(dto.titulo() != null){
            this.titulo = dto.titulo();
        }
        if(dto.descricao() != null){
            this.descricao = dto.descricao();
        }
    }

}
