package br.com.fiap.sistema.gestao.tarefas.service;

import br.com.fiap.sistema.gestao.tarefas.infra.exception.TaskCouldNotBeUpdatedException;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.StatusTarefa;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.Tarefa;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.AtualizarTarefaDTO;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.CriarTarefaDTO;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.ListarTarefasDTO;
import br.com.fiap.sistema.gestao.tarefas.model.usuario.Usuario;
import br.com.fiap.sistema.gestao.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public Tarefa criarTarefa(CriarTarefaDTO dto){
        var tarefa = new Tarefa(dto);
        var usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        tarefa.setUsuario((Usuario) usuario);
        ((Usuario) usuario).getTarefas().add(tarefa);
        repository.save(tarefa);
        return tarefa;
    }

    public List<ListarTarefasDTO> buscarTodasTarefas(){
        return repository.findAll().stream().map(ListarTarefasDTO::new).collect(Collectors.toList());
    }

    public List<Tarefa> retornarTarefasDoUsuario(){
        var usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findAllByUsuario((Usuario) usuario);
    }

    public Tarefa atualizarTarefa(AtualizarTarefaDTO dto, Long id){
        var tarefa = repository.getReferenceById(id);
        if (tarefa.getStatusTarefa() == StatusTarefa.CONCLUIDA){
            throw new TaskCouldNotBeUpdatedException();
        }
        tarefa.atualizar(dto);
        return tarefa;
    }

    public Tarefa abrirTarefa(Long id){
        var tarefa = repository.getReferenceById(id);
        if(tarefa.getStatusTarefa() == StatusTarefa.EM_ANDAMENTO){
            throw new TaskCouldNotBeUpdatedException();
        }
        tarefa.setStatusTarefa(StatusTarefa.EM_ANDAMENTO);
        return tarefa;
    }

}
