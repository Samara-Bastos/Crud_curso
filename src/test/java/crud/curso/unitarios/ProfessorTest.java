package crud.curso.unitarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
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
import org.springframework.data.domain.PageImpl;

import crud.curso.dto.ProfessorRequestDTO;
import crud.curso.dto.ProfessorResponseDTO;
import crud.curso.exceptions.FindProfessorException;
import crud.curso.fixture.ProfessorFixture;
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
        Professor professor = new Professor("Marcos","2020");

        when(professorRepository.findByRegistro(dtoRequestValido.registro())).thenReturn(Optional.of(professor));

        assertThrows(FindProfessorException.class, () -> {
            professorServiceImpl.cadastrar(dtoRequestValido);
        });

        verify(professorRepository, never()).save(any(Professor.class));
    }


    @Test
    @DisplayName("Deve permitir a exibição de todos os professores")
    void exibirTodos(){
        List<Professor> professor = new ArrayList<>();

        Page<Professor> professorPage = new PageImpl<>(professor);

        when(professorRepository.findAll(any(Pageable.class))).thenReturn(professorPage);

        Page<ProfessorResponseDTO> ProfessoresEncontrados = professorServiceImpl.findAll(Pageable.unpaged());

        assertTrue(ProfessoresEncontrados.isEmpty());
    }

    @Test 
    @DisplayName("Deve permitir a atualização dos dados")
    void atualizarTest(){

        Professor professor = new Professor("Marcos","2020");

        when(professorRepository.findByRegistro(dtoRequestValido.registro())).thenReturn(Optional.of(professor));

        ProfessorResponseDTO result = professorServiceImpl.atualizar("2020", dtoRequestValido);

        assertNotNull(result);
        assertEquals(result.nome(), dtoRequestValido.nome());
    }

    @Test 
    @DisplayName("Deve permitir a exclusão dos dados")
    void deletarTest(){

        Professor professor = new Professor("Marcos","2020");

        when(professorRepository.findByRegistro(dtoRequestValido.registro())).thenReturn(Optional.of(professor));

        professorServiceImpl.deletar("2020");

        verify(professorRepository, times(1)).delete(professor);
    }

}
