package crud.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/exibir")
    public Page<ProfessorResponseDTO> exibir(@PageableDefault(size = 3, sort = "nome", direction = Sort.Direction.ASC) Pageable paginacao){
        return professorService.findAll(paginacao);
    }

    @PatchMapping("/atualizar/{registro}")
    public ResponseEntity<ProfessorResponseDTO> atualizar(@PathVariable String registro, ProfessorRequestDTO professorRequestDTO){
        ProfessorResponseDTO professorResponseDTO = professorService.atualizar(registro, professorRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(professorResponseDTO);
    }

    @DeleteMapping("/deletar/{registro}")
	public ResponseEntity<Void> deletar(@PathVariable String registro) {
		professorService.deletar(registro);
		return ResponseEntity.noContent().build();
	}
}
