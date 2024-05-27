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
import crud.curso.service.AlunoServiceImpl;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoServiceImpl alunoServiceImpl;
    
    @PostMapping("/create")
    public ResponseEntity<AlunoResponseDTO> cadastrar(@RequestBody AlunoRequestDTO alunoRequestDTO){
        AlunoResponseDTO alunoResponseDTO = alunoServiceImpl.cadastrar(alunoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoResponseDTO);
    }
    
}
