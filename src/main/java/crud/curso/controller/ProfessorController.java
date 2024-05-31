package crud.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crud.curso.dto.ProfessorRequestDTO;
import crud.curso.dto.ProfessorResponseDTO;
import crud.curso.service.ProfessorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/professor") 
public class ProfessorController {
    
    @Autowired
    ProfessorService professorService;

    @PostMapping("/cadastro")
    public ResponseEntity<ProfessorResponseDTO> cadastrar(@Valid @RequestBody ProfessorRequestDTO professorRequestDTO){
        ProfessorResponseDTO professorResponseDTO = professorService.cadastrar(professorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorResponseDTO);
    }
}
