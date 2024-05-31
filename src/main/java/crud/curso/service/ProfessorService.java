package crud.curso.service;

import crud.curso.dto.ProfessorRequestDTO;
import crud.curso.dto.ProfessorResponseDTO;

public interface ProfessorService {

    ProfessorResponseDTO cadastrar(ProfessorRequestDTO professorRequestDTO);

    ProfessorResponseDTO atualizar(String registro, ProfessorRequestDTO professorRequestDTO);

    void deletar(String registro);
}

