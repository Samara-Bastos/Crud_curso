package crud.curso.dto;

import jakarta.validation.constraints.NotBlank;

public record CursoRequestDTO(

    @NotBlank(message = "O campo nome deve ser preenchido")
    String nome,

    @NotBlank(message = "O campo codigo deve ser preenchido")
    String codigo,

    String registro_professor

) {}
