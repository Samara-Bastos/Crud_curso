package crud.curso.dto;

import crud.curso.model.Curso;
import java.util.List;

public record ProfessorResponseDTO(

    String nome,

    String registro,

    List<Curso> cursos

) {}
