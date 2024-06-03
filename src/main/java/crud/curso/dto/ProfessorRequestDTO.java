package crud.curso.dto;

import jakarta.validation.constraints.NotBlank;

public record ProfessorRequestDTO(

    @NotBlank(message = "O campo nome deve ser preenchido")
    String nome,

    @NotBlank(message = "O campo registro deve ser preenchido")
    String registro
    
) {} 
