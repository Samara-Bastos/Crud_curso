package crud.curso.fixture;

import crud.curso.dto.AlunoRequestDTO;

public class AlunoFixture {
    
    public static AlunoRequestDTO alunoRequestValido(){
        return new AlunoRequestDTO("Marcela", "20241515");
    }

    public static AlunoRequestDTO alunoRequestInvalido(){
        return new AlunoRequestDTO("Lorenzo", " ");
    }
}
