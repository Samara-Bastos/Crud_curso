package crud.curso.dto;

import crud.curso.model.Professor;

public record ProfessorResponseDTO(

    String nome,

    String registro

) {
    public ProfessorResponseDTO(Professor professor){
        this(professor.getNome(), professor.getRegistro());
    }
}
