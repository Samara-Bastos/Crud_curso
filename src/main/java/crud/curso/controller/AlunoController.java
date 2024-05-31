package crud.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crud.curso.dto.AlunoRequestDTO;
import crud.curso.dto.AlunoResponseDTO;
import crud.curso.service.AlunoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoService alunoService;
    
    @PostMapping("/cadastro")
    public ResponseEntity<AlunoResponseDTO> cadastrar(@Valid @RequestBody AlunoRequestDTO alunoRequestDTO){
        AlunoResponseDTO alunoResponseDTO = alunoService.cadastrar(alunoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoResponseDTO);
    }
    
}
