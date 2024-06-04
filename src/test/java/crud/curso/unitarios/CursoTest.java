package crud.curso.unitarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import crud.curso.dto.CursoRequestDTO;
import crud.curso.dto.CursoResponseDTO;
import crud.curso.exceptions.FindCursoException;
import crud.curso.fixture.CursoFixture;
import crud.curso.model.Curso;
import crud.curso.model.Professor;
import crud.curso.repository.CursoRepository;
import crud.curso.repository.ProfessorRepository;
import crud.curso.service.CursoServiceImpl;

import org.springframework.data.domain.PageImpl;

@ExtendWith(MockitoExtension.class)
public class CursoTest {
    
    @Mock
    CursoRepository cursoRepository;

    @Mock
    ProfessorRepository professorRepository;

    @InjectMocks
    CursoServiceImpl cursoServiceImpl;

    private CursoRequestDTO dtoRequestValido = CursoFixture.cursoRequestValido();

    @Test
    @DisplayName("Deve permitir a inserção de um curso")
    void cadastrar(){

        Professor professor = new Professor("Marcos","2020");

        when(cursoRepository.findByCodigo(dtoRequestValido.codigo())).thenReturn(Optional.empty());
        when(professorRepository.findByRegistro(dtoRequestValido.registro_professor())).thenReturn(Optional.of(professor));

        CursoResponseDTO result = cursoServiceImpl.cadastrar(dtoRequestValido);

        assertNotNull(result);
        assertEquals(result.nome(), dtoRequestValido.nome());

    }

    @Test
    @DisplayName("Não deve permitir a inserção de um curso")
    void cadastrarException(){
        
        Curso curso = new Curso("Algoritmo", "A031");

        when(cursoRepository.findByCodigo(dtoRequestValido.codigo())).thenReturn(Optional.of(curso));

        assertThrows(FindCursoException.class, () -> {
            cursoServiceImpl.cadastrar(dtoRequestValido);
        });

        verify(cursoRepository, never()).save(any(Curso.class));
    }

    @Test
    @DisplayName("Deve permitir a exibição de todos os cursos")
    void exibirTodos(){
        List<Curso> curso = new ArrayList<>();

        Page<Curso> cursoPage = new PageImpl<>(curso);

        when(cursoRepository.findAll(any(Pageable.class))).thenReturn(cursoPage);

        Page<CursoResponseDTO> cursosEncontrados = cursoServiceImpl.findAll(Pageable.unpaged());

        assertTrue(cursosEncontrados.isEmpty());
    }
}
