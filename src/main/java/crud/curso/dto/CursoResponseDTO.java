package crud.curso.dto;

import java.util.List;
import crud.curso.model.Aluno;
import crud.curso.model.Professor;

public record CursoResponseDTO(

    String nome,

    String codigo,

    Professor professor,

    List<Aluno> alunos
) {}
