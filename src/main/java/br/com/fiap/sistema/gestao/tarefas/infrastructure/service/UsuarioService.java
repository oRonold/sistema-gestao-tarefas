package br.com.fiap.sistema.gestao.tarefas.infrastructure.service;

import br.com.fiap.sistema.gestao.tarefas.infrastructure.repository.UsuarioRepository;
import br.com.fiap.sistema.gestao.tarefas.domain.usuario.Usuario;
import br.com.fiap.sistema.gestao.tarefas.domain.usuario.dto.CriarUsuarioDTO;
import br.com.fiap.sistema.gestao.tarefas.domain.usuario.dto.LoginUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService service;

    public Usuario cadastrar(CriarUsuarioDTO dto){
        var usuario = new Usuario(dto, passwordEncoder.encode(dto.senha()));
        userRepository.save(usuario);
        return usuario;
    }

    public String login(LoginUsuarioDTO dto){
        var token = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = authenticationManager.authenticate(token);
        return service.criarToken((Usuario) authentication.getPrincipal());
    }
}
