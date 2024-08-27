package br.com.fiap.sistema.gestao.tarefas.service;

import br.com.fiap.sistema.gestao.tarefas.model.tarefa.Tarefa;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.CriarTarefaDTO;
import br.com.fiap.sistema.gestao.tarefas.model.usuario.Usuario;
import br.com.fiap.sistema.gestao.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

}
