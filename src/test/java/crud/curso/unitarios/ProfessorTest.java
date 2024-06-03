package crud.curso.unitarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import crud.curso.dto.ProfessorRequestDTO;
import crud.curso.dto.ProfessorResponseDTO;
import crud.curso.exceptions.FindProfessorException;
import crud.curso.fixture.ProfessorFixture;
import crud.curso.model.Curso;
import crud.curso.model.Professor;
import crud.curso.repository.ProfessorRepository;
import crud.curso.service.ProfessorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProfessorTest {
    
    @Mock
    ProfessorRepository professorRepository;

    @InjectMocks
    ProfessorServiceImpl professorServiceImpl;

    private ProfessorRequestDTO dtoRequestValido = ProfessorFixture.professorRequestValido();


    @Test
    @DisplayName("Deve permitir a inserção de um professor")
    void cadastrar(){

        when(professorRepository.findByRegistro(dtoRequestValido.registro())).thenReturn(Optional.empty());

        ProfessorResponseDTO result = professorServiceImpl.cadastrar(dtoRequestValido);

        assertNotNull(result);
        assertEquals(result.nome(), dtoRequestValido.nome());

    }

    @Test
    @DisplayName("Não deve permitir a inserção de um professor")
    void cadastrarException(){
        List<Curso> cursosVazios = new ArrayList<>();
        Professor professor = new Professor(1, "Laura", "1010",cursosVazios);

        when(professorRepository.findByRegistro(dtoRequestValido.registro())).thenReturn(Optional.of(professor));

        assertThrows(FindProfessorException.class, () -> {
            professorServiceImpl.cadastrar(dtoRequestValido);
        });

        verify(professorRepository, never()).save(any(Professor.class));
    }
}
