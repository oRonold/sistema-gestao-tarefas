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
        return ResponseEntity.badRequest().body(new TarefaJaAbertaDTO("A tarefa já está em andamento"));
    }

}
