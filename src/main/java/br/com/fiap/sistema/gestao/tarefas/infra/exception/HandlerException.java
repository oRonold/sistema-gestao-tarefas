package br.com.fiap.sistema.gestao.tarefas.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> exceptionIllegalValue(){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> erro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TaskCouldNotBeUpdatedException.class)
    public ResponseEntity<TarefaJaAbertaDTO> tarefaJaAberta(){
        return ResponseEntity.badRequest().body(new TarefaJaAbertaDTO("Esta tarefa está pendente ou já está concluida, caso esteja pendente, não pode se tornar concluida"));
    }

    @ExceptionHandler(TaskDoesNotBelongToUserException.class)
    public ResponseEntity<TarefaNaoPertenceAoUsuarioDTO> tarefaNaoPertence(){
        return ResponseEntity.badRequest().body(new TarefaNaoPertenceAoUsuarioDTO("A tarefa não pertence a este Usuário ou não existe"));
    }

}
