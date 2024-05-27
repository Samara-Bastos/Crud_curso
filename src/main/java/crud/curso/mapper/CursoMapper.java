package crud.curso.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import crud.curso.dto.CursoRequestDTO;
import crud.curso.dto.CursoResponseDTO;
import crud.curso.model.Curso;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);

    Curso requestToCurso(CursoRequestDTO cursoRequestDTO);

    CursoResponseDTO cursoToResponseDTO(Curso curso);
    
}
