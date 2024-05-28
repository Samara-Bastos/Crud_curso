package crud.curso.service;

import org.springframework.stereotype.Service;

import crud.curso.dto.AlunoRequestDTO;
import crud.curso.dto.AlunoResponseDTO;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Override
    public AlunoResponseDTO cadastrar(AlunoRequestDTO alunoRequestDTO){
        
        AlunoResponseDTO AlunoResponseDTO = null;
        return AlunoResponseDTO;
    };

    @Override
    public AlunoResponseDTO atualizar(String matricula, AlunoRequestDTO alunoRequestDTO){
        
        AlunoResponseDTO AlunoResponseDTO = null;
        return AlunoResponseDTO;
    };

    @Override
    public void deletar(String matricula){

    };
    
}
