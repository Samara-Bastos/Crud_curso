package crud.curso.fixture;

import crud.curso.dto.ProfessorRequestDTO;

public class ProfessorFixture {
    
    public static ProfessorRequestDTO professorRequestValido(){
        return new ProfessorRequestDTO("Marcia Dolores", "1010");
    }

    public static ProfessorRequestDTO professorRequestInvalido(){
        return new ProfessorRequestDTO(" ", "5656");
    }
}
