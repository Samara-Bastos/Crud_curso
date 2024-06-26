package crud.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crud.curso.dto.AlunoRequestDTO;
import crud.curso.dto.AlunoResponseDTO;
import crud.curso.exceptions.FindAlunoException;
import crud.curso.exceptions.NotFoundAlunoException;
import crud.curso.exceptions.NotFoundCursoException;
import crud.curso.mapper.AlunoMapper;
import crud.curso.model.Aluno;
import crud.curso.model.Curso;
import crud.curso.repository.AlunoRepository;
import crud.curso.repository.CursoRepository;
import jakarta.transaction.Transactional;

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
        
        Aluno aluno = AlunoMapper.INSTANCE.dtoToAluno(alunoRequestDTO);

        alunoRepository.save(aluno);

        AlunoResponseDTO AlunoResponseDTO = AlunoMapper.INSTANCE.alunoToResponseDTO(aluno);
        return AlunoResponseDTO;
    };

    @Override
    @Transactional
    public AlunoResponseDTO atualizar(String matricula, AlunoRequestDTO alunoRequestDTO){

        Optional<Aluno> alunoBuscado = alunoRepository.findByMatricula(matricula);

        if(alunoBuscado.isEmpty()){
            throw new NotFoundAlunoException("Não existe nenhum aluno cadastrado com essa matricula");
        }   
        
        Aluno alunoNovo = AlunoMapper.INSTANCE.dtoToAluno(alunoRequestDTO);

        alunoBuscado.get().setNome(alunoNovo.getNome());
        alunoBuscado.get().setMatricula(alunoNovo.getMatricula());

        alunoRepository.save(alunoBuscado.get());

        AlunoResponseDTO AlunoResponseDTO = AlunoMapper.INSTANCE.alunoToResponseDTO(alunoBuscado.get());
        return AlunoResponseDTO;
    };

    @Override
    @Transactional
    public void deletar(String matricula){

        Optional<Aluno> alunoBuscado = alunoRepository.findByMatricula(matricula);

        if(alunoBuscado.isEmpty()){
            throw new NotFoundAlunoException("Não existe nenhum aluno cadastrado com essa matricula");
        } 

        Aluno aluno = alunoBuscado.get();

        if(aluno.getCursos() != null){
            for (Curso curso : aluno.getCursos()) {
                curso.getAlunos().remove(aluno);
                cursoRepository.save(curso);
            }
        }

        alunoRepository.delete(aluno);
    };

    @Override
    @Transactional
    public void matricular(String matricula, String codigo){

        Optional<Aluno> alunoBuscado = alunoRepository.findByMatricula(matricula);

        if(alunoBuscado.isEmpty()){
            throw new NotFoundAlunoException("Não existe nenhum aluno cadastrado com essa matricula");
        }

        Optional<Curso> cursoBuscado = cursoRepository.findByCodigo(codigo);
                                                                                                                         
        if(cursoBuscado.isEmpty()){
            throw new NotFoundCursoException("Não existe nenhum curso cadastrado com esse código");
        } 
        
        alunoRepository.matricularAluno(alunoBuscado.get().getId(),cursoBuscado.get().getId());

    };

    @Override
    @Transactional
    public void desmatricular(String matricula, String codigo){

        Optional<Aluno> alunoBuscado = alunoRepository.findByMatricula(matricula);

        if(alunoBuscado.isEmpty()){
            throw new NotFoundAlunoException("Não existe nenhum aluno cadastrado com essa matricula");
        }

        Optional<Curso> cursoBuscado = cursoRepository.findByCodigo(codigo);
                                                                                                                         
        if(cursoBuscado.isEmpty()){
            throw new NotFoundCursoException("Não existe nenhum curso cadastrado com esse código");
        } 
        
        alunoRepository.desmatricularAluno(alunoBuscado.get().getId(),cursoBuscado.get().getId());

    };

    @Override
    public Page<AlunoResponseDTO> findAll(Pageable paginacao){
        return alunoRepository.findAll(paginacao).map(aluno -> {
                return new AlunoResponseDTO(aluno);
        });
    }
    
}
