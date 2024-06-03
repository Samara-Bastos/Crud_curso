package crud.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import crud.curso.dto.ProfessorRequestDTO;
import crud.curso.dto.ProfessorResponseDTO;
import crud.curso.exceptions.FindProfessorException;
import crud.curso.mapper.ProfessorMapper;
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
    public ProfessorResponseDTO atualizar(String codigo, ProfessorRequestDTO professorRequestDTO){

        ProfessorResponseDTO professorResponseDTO = null;
        return professorResponseDTO;
    }

    @Override 
    @Transactional
    public void deletar(String codigo){

    }

    @Override
    public Page<ProfessorResponseDTO> findAll(Pageable paginacao){
        return professorRepository.findAll(paginacao).map(professor -> {
                return new ProfessorResponseDTO(professor);
        });
    }
}
