package crud.curso.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import crud.curso.dto.AlunoRequestDTO;
import crud.curso.dto.AlunoResponseDTO;
import crud.curso.model.Aluno;

@Mapper(componentModel = "spring")
public interface AlunoMapper {
    
    AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    Aluno requestDTOToAluno(AlunoRequestDTO alunoRequestDTO);

    AlunoResponseDTO alunoToResponseDTO(Aluno aluno);

}
