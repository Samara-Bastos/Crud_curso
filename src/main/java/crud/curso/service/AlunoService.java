package crud.curso.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import crud.curso.dto.AlunoRequestDTO;
import crud.curso.dto.AlunoResponseDTO;

public interface AlunoService {

    public AlunoResponseDTO cadastrar(AlunoRequestDTO alunoRequestDTO);

    public AlunoResponseDTO atualizar(String matricula, AlunoRequestDTO alunoRequestDTO);

    public void deletar(String matricula);

    public void matricular(String matricula, String codigo);

    public void desmatricular(String matricula, String codigo);

    public Page<AlunoResponseDTO> findAll(Pageable paginacao);
    
}
