package br.com.fiap.sistema.gestao.tarefas.service;

import br.com.fiap.sistema.gestao.tarefas.model.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String tokenPass;

    public String criarToken(Usuario usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(tokenPass);
            return JWT.create()
                    .withIssuer("GESTAO TAREFAS")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                    .sign(algoritmo);
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token JWT");
        }
    }
}
