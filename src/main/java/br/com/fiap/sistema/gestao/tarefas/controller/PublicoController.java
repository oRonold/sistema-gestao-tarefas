package br.com.fiap.sistema.gestao.tarefas.controller;

import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.DetalhesTarefaDTO;
import br.com.fiap.sistema.gestao.tarefas.model.tarefa.dto.ListarTarefasDTO;
import br.com.fiap.sistema.gestao.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicoController {

    @Autowired
    private TarefaService service;

    @GetMapping("/status")
    public ResponseEntity<List<ListarTarefasDTO>> listarTarefasPublicas(){
        var tarefas = service.buscarTodasTarefas();
        return ResponseEntity.ok().body(tarefas);
    }
}
