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

import crud.curso.dto.ProfessorRequestDTO;
import crud.curso.fixture.ProfessorFixture;
import crud.curso.fixture.SqlProvider;
import crud.curso.repository.ProfessorRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ProfessorTest {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    ProfessorRepository professorRepository;

    private ProfessorRequestDTO dto;

    private String json;


    @Test
    @DisplayName("Deve permitir cadastrar um professor")
    void cadastrarTest() throws Exception {

        dto = ProfessorFixture.professorRequestValido();
        json = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/professor/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(dto.nome()));
    }

    @Test
    @DisplayName("Deve permitir exibir os professores cadastradas")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertAluno),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertProfessor),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertCurso),
        //@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void exibirTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
            .get("/professor/exibir"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].nome").value("Davi"));
    }

    @Test
    @DisplayName("Deve permitir atualizar um professor")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertAluno),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertProfessor),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertCurso),
        //@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void atualizarTest() throws Exception{
        String registro = "2020";

        dto = ProfessorFixture.professorRequestValido();
        json = mapper.writeValueAsString(dto);

        mockMvc
            .perform(MockMvcRequestBuilders.patch("/professor/atualizar/" + registro)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Deve permitir deletar um professor")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertAluno),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertProfessor),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertCurso),
        //@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void deletarTest() throws Exception {
        String registro = "2020";

        mockMvc
            .perform(MockMvcRequestBuilders.delete("/professor/deletar/" + registro))
            .andExpect(status().isNoContent());
    }

}
