package br.com.fiap.sistema.gestao.tarefas.controller;

import br.com.fiap.sistema.gestao.tarefas.model.dto.TokenDTO;
import br.com.fiap.sistema.gestao.tarefas.model.usuario.dto.CriarUsuarioDTO;
import br.com.fiap.sistema.gestao.tarefas.model.usuario.dto.DetalhesUsuarioDTO;
import br.com.fiap.sistema.gestao.tarefas.model.usuario.dto.LoginUsuarioDTO;
import br.com.fiap.sistema.gestao.tarefas.service.UsuarioService;
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
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDTO> cadastrar(@RequestBody @Valid CriarUsuarioDTO dto, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.cadastrar(dto);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDTO(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginUsuarioDTO dto) {
        var token = usuarioService.login(dto);
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
