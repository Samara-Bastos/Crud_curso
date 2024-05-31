package crud.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crud.curso.dto.CursoRequestDTO;
import crud.curso.dto.CursoResponseDTO;
import crud.curso.exceptions.FindCursoException;
import crud.curso.mapper.CursoMapper;
import crud.curso.repository.CursoRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import crud.curso.model.Curso;

@Service
public class CursoServiceImpl implements CursoService {
    
    @Autowired
    CursoRepository cursoRepository;


    @Override 
    @Transactional
    public CursoResponseDTO cadastrar(CursoRequestDTO cursoRequestDTO){

        Optional<Curso> cursoBuscado = cursoRepository.findByCodigo(cursoRequestDTO.codigo());

        if(cursoBuscado.isPresent()){
            throw new FindCursoException("Já existe um curso cadastrado com esse código");
        }

        Curso curso = CursoMapper.INSTANCE.requestToCurso(cursoRequestDTO);

        cursoRepository.save(curso);

        CursoResponseDTO cursoResponseDTO = CursoMapper.INSTANCE.cursoToResponseDTO(curso);
        return cursoResponseDTO;
    }

    @Override 
    @Transactional
    public CursoResponseDTO atualizar(String codigo, CursoRequestDTO cursoRequestDTO){

        CursoResponseDTO cursoResponseDTO = null;
        return cursoResponseDTO;
    }

    @Override 
    @Transactional
    public void deletar(String codigo){

    }

     @Override
    public Page<CursoResponseDTO> findAll(Pageable paginacao){
        return cursoRepository.findAll(paginacao).map(curso -> {
                return new CursoResponseDTO(curso);
        });
    }
    
}
