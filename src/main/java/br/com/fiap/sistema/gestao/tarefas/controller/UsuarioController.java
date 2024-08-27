package br.com.fiap.sistema.gestao.tarefas.controller;

import br.com.fiap.sistema.gestao.tarefas.infra.security.SecurityConfiguration;
import br.com.fiap.sistema.gestao.tarefas.model.dto.TokenDTO;
import br.com.fiap.sistema.gestao.tarefas.model.usuario.Usuario;
import br.com.fiap.sistema.gestao.tarefas.model.usuario.dto.CriarUsuarioDTO;
import br.com.fiap.sistema.gestao.tarefas.model.usuario.dto.DetalhesUsuarioDTO;
import br.com.fiap.sistema.gestao.tarefas.model.usuario.dto.LoginUsuarioDTO;
import br.com.fiap.sistema.gestao.tarefas.repository.UsuarioRepository;
import br.com.fiap.sistema.gestao.tarefas.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService service;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDTO> cadastrar(@RequestBody @Valid CriarUsuarioDTO dto, UriComponentsBuilder builder){
        var usuario = new Usuario(dto, passwordEncoder.encode(dto.senha()));
        userRepository.save(usuario);
        var uri = builder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDTO(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginUsuarioDTO dto){
        var token = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = authenticationManager.authenticate(token);
        var tokenJwt = service.criarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(tokenJwt));
    }
}
