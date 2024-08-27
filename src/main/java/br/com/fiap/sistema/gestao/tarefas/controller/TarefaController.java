package br.com.fiap.sistema.gestao.tarefas.controller;

import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.CriarTarefaDTO;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.DetalhesTarefaDTO;
import br.com.fiap.sistema.gestao.tarefas.repository.TarefaRepository;
import br.com.fiap.sistema.gestao.tarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesTarefaDTO> criar(@RequestBody @Valid CriarTarefaDTO dto, UriComponentsBuilder builder){
        var tarefa = service.criarTarefa(dto);
        var uri = builder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesTarefaDTO(tarefa));
    }
}
