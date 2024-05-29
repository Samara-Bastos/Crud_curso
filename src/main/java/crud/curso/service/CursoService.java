package crud.curso.service;

import crud.curso.dto.CursoRequestDTO;
import crud.curso.dto.CursoResponseDTO;

public interface CursoService {
    
    CursoResponseDTO cadastrar(CursoRequestDTO cursoRequestDTO);

    CursoResponseDTO atualizar(String codigo, CursoRequestDTO cursoRequestDTO);

    void deletar(String codigo);
    
}
