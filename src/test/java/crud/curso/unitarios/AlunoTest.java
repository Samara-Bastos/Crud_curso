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

import crud.curso.dto.AlunoRequestDTO;
import crud.curso.dto.AlunoResponseDTO;
import crud.curso.exceptions.FindAlunoException;
import crud.curso.fixture.AlunoFixture;
import crud.curso.model.Aluno;
import crud.curso.repository.AlunoRepository;
import crud.curso.repository.CursoRepository;
import crud.curso.service.AlunoServiceImpl;

import org.springframework.data.domain.PageImpl;

@ExtendWith(MockitoExtension.class)
public class AlunoTest {
    
    @Mock
    AlunoRepository alunoRepository;

    @Mock
    CursoRepository cursoRepository;

    @InjectMocks
    AlunoServiceImpl alunoServiceImpl;

    private AlunoRequestDTO dtoRequestValido = AlunoFixture.alunoRequestValido();

    @Test
    @DisplayName("Deve permitir a inserção de um aluno")
    void cadastrarTest(){

        when(alunoRepository.findByMatricula(dtoRequestValido.matricula())).thenReturn(Optional.empty());

        AlunoResponseDTO result = alunoServiceImpl.cadastrar(dtoRequestValido);

        assertNotNull(result);
        assertEquals(result.nome(), dtoRequestValido.nome());

    }

    @Test
    @DisplayName("Não deve permitir a inserção de um aluno")
    void cadastrarExceptionTest(){
        
        Aluno aluno = new Aluno("Luana","20241515");

        when(alunoRepository.findByMatricula(dtoRequestValido.matricula())).thenReturn(Optional.of(aluno));

        assertThrows(FindAlunoException.class, () -> {
            alunoServiceImpl.cadastrar(dtoRequestValido);
        });

        verify(alunoRepository, never()).save(any(Aluno.class));
    }

    @Test
    @DisplayName("Deve permitir a exibição de todos os alunos")
    void exibirTodosTest(){
        List<Aluno> aluno = new ArrayList<>();

        Page<Aluno> alunoPage = new PageImpl<>(aluno);

        when(alunoRepository.findAll(any(Pageable.class))).thenReturn(alunoPage);

        Page<AlunoResponseDTO> alunosEncontrados = alunoServiceImpl.findAll(Pageable.unpaged());

        assertTrue(alunosEncontrados.isEmpty());
    }

    @Test 
    @DisplayName("Deve permitir a atualização dos dados")
    void atualizarTest(){

        Aluno aluno = new Aluno("Luana","20241515");

        when(alunoRepository.findByMatricula(dtoRequestValido.matricula())).thenReturn(Optional.of(aluno));

        AlunoResponseDTO result = alunoServiceImpl.atualizar("20241515", dtoRequestValido);

        assertNotNull(result);
        assertEquals(result.nome(), dtoRequestValido.nome());
    }
}
