package crud.curso.dto;

import jakarta.validation.constraints.NotBlank;

public record AlunoRequestDTO(
    
    @NotBlank(message =  "O nome deve ser preenchido")
    String nome,

    @NotBlank(message =  "A matricula deve ser preenchida")
    String matricula,

    String codigo
    
) {}
