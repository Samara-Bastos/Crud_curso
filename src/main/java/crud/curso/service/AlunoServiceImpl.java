package crud.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crud.curso.dto.AlunoRequestDTO;
import crud.curso.dto.AlunoResponseDTO;
import crud.curso.exceptions.FindAlunoException;
import crud.curso.exceptions.NotFoundCursoException;
import crud.curso.mapper.AlunoMapper;
import crud.curso.model.Aluno;
import crud.curso.model.Curso;
import crud.curso.repository.AlunoRepository;
import crud.curso.repository.CursoRepository;
import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Override
    @Transactional
    public AlunoResponseDTO cadastrar(AlunoRequestDTO alunoRequestDTO){

        Optional<Aluno> alunoBuscado = alunoRepository.findByMatricula(alunoRequestDTO.matricula());

        if(alunoBuscado.isPresent()){
            throw new FindAlunoException("Ja existe um aluno cadastrado com essa matricula");
        }

        Optional<Curso> cursoBuscado = cursoRepository.findByCodigo(alunoRequestDTO.codigo_curso());
                                                                                                                         
        if(cursoBuscado.isEmpty()){
            throw new NotFoundCursoException("Não existe nenhum curso com esse código");
        }    
        
        Aluno aluno = AlunoMapper.INSTANCE.dtoToAluno(alunoRequestDTO);

        aluno.setCursos(Collections.singletonList(cursoBuscado.get()));

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

    @Override
    public Page<AlunoResponseDTO> findAll(Pageable paginacao){
        return alunoRepository.findAll(paginacao).map(aluno -> {
                return new AlunoResponseDTO(aluno);
        });
    }
    
}
