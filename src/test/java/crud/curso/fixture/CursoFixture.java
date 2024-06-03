package crud.curso.fixture;

import crud.curso.dto.CursoRequestDTO;

public class CursoFixture {
    
    public static CursoRequestDTO cursoRequestValido(){
        return new CursoRequestDTO("Algoritimo", "A031", "1010");
    }

    public static CursoRequestDTO cursoRequestInvalido(){
        return new CursoRequestDTO("Ingles", " ", "1010");
    }
}
