package crud.curso.dto;

import crud.curso.model.Aluno;

public record AlunoResponseDTO(

    String nome,

    String matricula
    
) {
    public AlunoResponseDTO(Aluno aluno){
        this(aluno.getNome(), aluno.getMatricula());
    }
}
