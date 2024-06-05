package crud.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import crud.curso.dto.ProfessorRequestDTO;
import crud.curso.dto.ProfessorResponseDTO;
import crud.curso.exceptions.FindProfessorException;
import crud.curso.exceptions.NotFoundProfessorException;
import crud.curso.mapper.ProfessorMapper;
import crud.curso.model.Curso;
import crud.curso.model.Professor;
import crud.curso.repository.CursoRepository;
import crud.curso.repository.ProfessorRepository;
import jakarta.transaction.Transactional;

@Service
public class ProfessorServiceImpl implements ProfessorService{
    
    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Override 
    @Transactional
    public ProfessorResponseDTO cadastrar(ProfessorRequestDTO professorRequestDTO){

        Optional<Professor> professorBuscado = professorRepository.findByRegistro(professorRequestDTO.registro());

        if(professorBuscado.isPresent()){
            throw new FindProfessorException("Já existe um professor cadastrado com esse registro");
        }

        Professor professor = ProfessorMapper.INSTANCE.requestDTOToProfessor(professorRequestDTO);

        professorRepository.save(professor);

        ProfessorResponseDTO professorResponseDTO = ProfessorMapper.INSTANCE.professorToResponseDTO(professor);
        return professorResponseDTO;
    }

    @Override 
    @Transactional
    public ProfessorResponseDTO atualizar(String registro, ProfessorRequestDTO professorRequestDTO){

        Optional<Professor> professorBuscado = professorRepository.findByRegistro(registro);

        if(professorBuscado.isEmpty()){
            throw new NotFoundProfessorException("Não existe nenhum professor cadastrado com esse registro");
        }

        Professor professorNovo = ProfessorMapper.INSTANCE.requestDTOToProfessor(professorRequestDTO);

        professorBuscado.get().setNome(professorNovo.getNome());
        professorBuscado.get().setRegistro(professorNovo.getRegistro());

        professorRepository.save(professorBuscado.get());

        ProfessorResponseDTO professorResponseDTO = ProfessorMapper.INSTANCE.professorToResponseDTO(professorBuscado.get());
        return professorResponseDTO;
    }

    @Override 
    @Transactional
    public void deletar(String registro){
        
        Optional<Professor> professorBuscado = professorRepository.findByRegistro(registro);

        if(professorBuscado.isEmpty()){
            throw new NotFoundProfessorException("Não existe nenhum professor cadastrado com esse registro");
        }

        Professor professor = professorBuscado.get();

        if(professor.getCursos() != null){
            for (Curso curso : professor.getCursos()) {
                curso.setProfessor(null);
                cursoRepository.save(curso);
            }
        }

        professorRepository.delete(professorBuscado.get());
    }

    @Override
    public Page<ProfessorResponseDTO> findAll(Pageable paginacao){
        return professorRepository.findAll(paginacao).map(professor -> {
                return new ProfessorResponseDTO(professor);
        });
    }
}
