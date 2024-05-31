package crud.curso.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import crud.curso.dto.ProfessorRequestDTO;
import crud.curso.dto.ProfessorResponseDTO;
import crud.curso.model.Professor;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    
    ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);

    Professor requestDTOToProfessor(ProfessorRequestDTO professorRequestDTO);

    ProfessorResponseDTO professorToResponseDTO(Professor professor);
    
}
