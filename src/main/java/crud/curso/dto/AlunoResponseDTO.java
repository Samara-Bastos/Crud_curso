package crud.curso.dto;

import java.util.List;
import crud.curso.model.Curso;

public record AlunoResponseDTO(

    String nome,

    String matricula,

    List<Curso> cursos
    
) {}
