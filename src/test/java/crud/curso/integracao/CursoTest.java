package crud.curso.integracao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import crud.curso.dto.CursoRequestDTO;
import crud.curso.fixture.CursoFixture;
import crud.curso.fixture.SqlProvider;
import crud.curso.repository.CursoRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class CursoTest {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    CursoRepository cursoRepository;

    private CursoRequestDTO dto;

    private String json;

    @Test
    @DisplayName("Deve permitir cadastrar um curso")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertProfessor),
        //@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void cadastrarTest() throws Exception {

        dto = CursoFixture.cursoRequestValido();
        json = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/curso/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(dto.nome()));
    }


    @Test
    @DisplayName("Deve permitir exibir os cursos cadastradas")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertAluno),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertProfessor),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertCurso),
        //@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void exibirTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
            .get("/curso/exibir"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].nome").value("Algoritmo"));
    }


    @Test
    @DisplayName("Deve permitir atualizar um curso")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertAluno),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertProfessor),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertCurso),
        //@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void atualizarTest() throws Exception{
        String codigo = "2222";

        dto = CursoFixture.cursoRequestValido();
        json = mapper.writeValueAsString(dto);

        mockMvc
            .perform(MockMvcRequestBuilders.patch("/curso/atualizar/" + codigo)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Deve permitir deletar um curso")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertAluno),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertProfessor),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertCurso),
        //@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void deletarTest() throws Exception {
        String codigo = "2222";

        mockMvc
            .perform(MockMvcRequestBuilders.delete("/curso/deletar/" + codigo))
            .andExpect(status().isNoContent());
    }
}
