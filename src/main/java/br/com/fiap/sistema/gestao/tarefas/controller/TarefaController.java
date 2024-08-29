package br.com.fiap.sistema.gestao.tarefas.controller;

import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.AtualizarTarefaDTO;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.CriarTarefaDTO;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.DetalhesTarefaDTO;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.ListarTarefasDTO;
import br.com.fiap.sistema.gestao.tarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/public/status")
    public ResponseEntity<List<ListarTarefasDTO>> buscarTodas(){
        var page = service.buscarTodasTarefas();
        return ResponseEntity.ok(page);
    }

    @GetMapping
    public ResponseEntity<List<DetalhesTarefaDTO>> tarefasPessoais(){
        var tarefas = service.retornarTarefasDoUsuario().stream().map(DetalhesTarefaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(tarefas);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesTarefaDTO> atualizarTarefas(@RequestBody AtualizarTarefaDTO dto, @PathVariable Long id){
        var tarefa = service.atualizarTarefa(dto, id);
        return ResponseEntity.ok().body(new DetalhesTarefaDTO(tarefa));
    }

    @PutMapping("/{id}/abrir")
    @Transactional
    public ResponseEntity<DetalhesTarefaDTO> abrirTarefa(@PathVariable Long id){
        var tarefa = service.abrirTarefa(id);
        return ResponseEntity.ok().body(new DetalhesTarefaDTO(tarefa));
    }

    @PutMapping("/{id}/fechar")
    @Transactional
    public ResponseEntity<DetalhesTarefaDTO> fecharTarefa(@PathVariable Long id){
        var tarefa = service.fecharTarefa(id);
        return ResponseEntity.ok().body(new DetalhesTarefaDTO(tarefa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id){
        service.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }

}
