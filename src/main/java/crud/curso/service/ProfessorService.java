package crud.curso.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import crud.curso.dto.ProfessorRequestDTO;
import crud.curso.dto.ProfessorResponseDTO;

public interface ProfessorService {

    ProfessorResponseDTO cadastrar(ProfessorRequestDTO professorRequestDTO);

    ProfessorResponseDTO atualizar(String registro, ProfessorRequestDTO professorRequestDTO);

    void deletar(String registro);

    public Page<ProfessorResponseDTO> findAll(Pageable paginacao);
}

