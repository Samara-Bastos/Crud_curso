package crud.curso.service;

import crud.curso.dto.AlunoRequestDTO;
import crud.curso.dto.AlunoResponseDTO;

public interface AlunoService {

    public AlunoResponseDTO cadastrar(AlunoRequestDTO alunoRequestDTO);

    public AlunoResponseDTO atualizar(String matricula, AlunoRequestDTO alunoRequestDTO);

    public void deletar(String matricula);

}
