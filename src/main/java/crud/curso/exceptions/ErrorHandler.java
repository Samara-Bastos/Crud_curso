package crud.curso.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundAlunoException.class)
    public ResponseEntity<String> handleErrorNotFoundAlunoException(NotFoundAlunoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundCursoException.class)
    public ResponseEntity<String> handleErrorNotFoundCursoException(NotFoundCursoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundProfessorException.class)
    public ResponseEntity<String> handleErrorNotFoundProfessorException(NotFoundProfessorException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(FindAlunoException.class)
    public ResponseEntity<String> handleErrorFindAlunoException(FindAlunoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(FindCursoException.class)
    public ResponseEntity<String> handleErrorFindCursoException(FindCursoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(FindProfessorException.class)
    public ResponseEntity<String> handleErrorFindProfessorException(FindProfessorException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
