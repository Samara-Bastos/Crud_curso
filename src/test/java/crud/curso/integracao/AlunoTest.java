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

import crud.curso.dto.AlunoRequestDTO;
import crud.curso.fixture.AlunoFixture;
import crud.curso.fixture.SqlProvider;
import crud.curso.repository.AlunoRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class AlunoTest {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    AlunoRepository alunoRepository;

    private AlunoRequestDTO dto;

    private String json;

    @Test
    @DisplayName("Deve permitir cadastrar um aluno")
    void cadastrarTest() throws Exception {

        dto = AlunoFixture.alunoRequestValido();
        json = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/aluno/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(dto.nome()));
    }

    @Test
    @DisplayName("Deve permitir exibir os alunos cadastradas")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertAluno),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertProfessor),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertCurso),
        //@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void exibirTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
            .get("/aluno/exibir"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].nome").value("Clara"));
    }

    @Test
    @DisplayName("Deve permitir atualizar um aluno")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertAluno)
        //@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void atualizarTest() throws Exception{
        String matricula = "20241111";

        dto = AlunoFixture.alunoRequestValido();
        json = mapper.writeValueAsString(dto);

        System.out.println("ta aqui oh"+matricula + json);

        mockMvc
            .perform(MockMvcRequestBuilders.patch("/aluno/atualizar/" + matricula)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Deve permitir deletar um aluno")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertAluno),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertProfessor),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertCurso),
        //@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void deletarTest() throws Exception {
        String matricula = "20241111";

        mockMvc
            .perform(MockMvcRequestBuilders.delete("/aluno/deletar/" + matricula))
            .andExpect(status().isNoContent());
    }
}
