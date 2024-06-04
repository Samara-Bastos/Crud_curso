package crud.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crud.curso.dto.CursoRequestDTO;
import crud.curso.dto.CursoResponseDTO;
import crud.curso.exceptions.FindCursoException;
import crud.curso.exceptions.NotFoundCursoException;
import crud.curso.exceptions.NotFoundProfessorException;
import crud.curso.mapper.CursoMapper;
import crud.curso.repository.CursoRepository;
import crud.curso.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import crud.curso.model.Curso;
import crud.curso.model.Professor;

@Service
public class CursoServiceImpl implements CursoService {
    
    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    ProfessorRepository professorRepository;


    @Override 
    @Transactional
    public CursoResponseDTO cadastrar(CursoRequestDTO cursoRequestDTO){

        Optional<Curso> cursoBuscado = cursoRepository.findByCodigo(cursoRequestDTO.codigo());

        if(cursoBuscado.isPresent()){
            throw new FindCursoException("Já existe um curso cadastrado com esse código");
        }

        Optional<Professor> professor = professorRepository.findByRegistro(cursoRequestDTO.registro_professor());

        if(professor.isEmpty()){
            throw new NotFoundProfessorException("Não existe um professor cadastrado com esse registro");
        }

        Curso curso = CursoMapper.INSTANCE.requestToCurso(cursoRequestDTO);

        curso.setProfessor(professor.get());

        cursoRepository.save(curso);

        CursoResponseDTO cursoResponseDTO = CursoMapper.INSTANCE.cursoToResponseDTO(curso);
        return cursoResponseDTO;
    }

    @Override 
    @Transactional
    public CursoResponseDTO atualizar(String codigo, CursoRequestDTO cursoRequestDTO){

        Optional<Curso> cursoBuscado = cursoRepository.findByCodigo(codigo);

        if(cursoBuscado.isEmpty()){
            throw new NotFoundCursoException("Não existe nenhum curso cadastrado com esse código");
        }

        Optional<Professor> professor = professorRepository.findByRegistro(cursoRequestDTO.registro_professor());

        if(professor.isEmpty()){
            throw new NotFoundProfessorException("Não existe nenhum professor cadastrado com esse registro");
        }

        Curso cursoNovo = CursoMapper.INSTANCE.requestToCurso(cursoRequestDTO);

        cursoBuscado.get().setNome(cursoNovo.getNome());
        cursoBuscado.get().setCodigo(cursoNovo.getCodigo());
        cursoBuscado.get().setProfessor(professor.get());

        cursoRepository.save(cursoBuscado.get());

        CursoResponseDTO cursoResponseDTO = CursoMapper.INSTANCE.cursoToResponseDTO(cursoBuscado.get());
        return cursoResponseDTO;
    }

    @Override 
    @Transactional
    public void deletar(String codigo){

        Optional<Curso> cursoBuscado = cursoRepository.findByCodigo(codigo);

        if(cursoBuscado.isEmpty()){
            throw new NotFoundCursoException("Não existe nenhum curso cadastrado com esse código");
        }

        cursoRepository.delete(cursoBuscado.get());
    }

     @Override
    public Page<CursoResponseDTO> findAll(Pageable paginacao){
        return cursoRepository.findAll(paginacao).map(curso -> {
                return new CursoResponseDTO(curso);
        });
    }
    
}
