package crud.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crud.curso.dto.AlunoRequestDTO;
import crud.curso.dto.AlunoResponseDTO;
import crud.curso.exceptions.FindAlunoException;
import crud.curso.mapper.AlunoMapper;
import crud.curso.model.Aluno;
import crud.curso.repository.AlunoRepository;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    @Transactional
    public AlunoResponseDTO cadastrar(AlunoRequestDTO alunoRequestDTO){

        Optional<Aluno> alunoBuscado = alunoRepository.findByMatricula(alunoRequestDTO.matricula());

        if(alunoBuscado.isPresent()){
            throw new FindAlunoException("Ja existe um aluno cadastrado com essa matricula");
        }
        
        Aluno aluno = AlunoMapper.INSTANCE.dtoToAluno(alunoRequestDTO);

        alunoRepository.save(aluno);

        AlunoResponseDTO AlunoResponseDTO = AlunoMapper.INSTANCE.alunoToResponseDTO(aluno);
        return AlunoResponseDTO;
    };

    @Override
    @Transactional
    public AlunoResponseDTO atualizar(String matricula, AlunoRequestDTO alunoRequestDTO){
        
        AlunoResponseDTO AlunoResponseDTO = null;
        return AlunoResponseDTO;
    };

    @Override
    @Transactional
    public void deletar(String matricula){

    };
    
}
