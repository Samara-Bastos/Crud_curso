package crud.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import crud.curso.dto.CursoRequestDTO;
import crud.curso.dto.CursoResponseDTO;
import crud.curso.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/curso")
public class CursoController {
    
    @Autowired
    CursoService cursoService;


    @PostMapping("/cadastro")
    public ResponseEntity<CursoResponseDTO> cadastrar(@Valid @RequestBody CursoRequestDTO cursoRequestDTO){
        CursoResponseDTO cursoResponseDTO = cursoService.cadastrar(cursoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoResponseDTO);
    }

    @GetMapping("/exibir")
    public Page<CursoResponseDTO> exibir(@PageableDefault(size = 3, sort = "nome", direction = Sort.Direction.ASC) Pageable paginacao){
        return cursoService.findAll(paginacao);
    }
    
    @PatchMapping("/atualizar/{codigo}")
    public ResponseEntity<CursoResponseDTO> atualizar(@PathVariable String codigo, CursoRequestDTO cursoRequestDTO){
        CursoResponseDTO cursoResponseDTO = cursoService.atualizar(codigo, cursoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(cursoResponseDTO);
    }

    @DeleteMapping("/deletar/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable String codigo) {
		cursoService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
}
