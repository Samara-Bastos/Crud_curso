package crud.curso.dto;

import java.util.List;
import crud.curso.model.Aluno;
import crud.curso.model.Curso;
import crud.curso.model.Professor;

public record CursoResponseDTO(

    String nome,

    String codigo,

    Professor professor,

    List<Aluno> alunos
) {

    public CursoResponseDTO(Curso curso){
        this(curso.getNome(), curso.getCodigo(), curso.getProfessor(), curso.getAlunos());
    }
}
