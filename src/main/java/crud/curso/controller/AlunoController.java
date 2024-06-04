package crud.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import crud.curso.dto.AlunoRequestDTO;
import crud.curso.dto.AlunoResponseDTO;
import crud.curso.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;

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
    
    @GetMapping("/exibir")
    public Page<AlunoResponseDTO> exibir(@PageableDefault(size = 3, sort = "nome", direction = Sort.Direction.ASC) Pageable paginacao){
        return alunoService.findAll(paginacao);
    }

    @PostMapping("/matricular/{matricula}/{codigo}")
    public ResponseEntity<Void> matricular(@PathVariable String matricula, @PathVariable String codigo){
        alunoService.matricular(matricula, codigo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/desmatricular/{matricula}/{codigo}")
    public void desmatricular(@PathVariable String matricula, @PathVariable String codigo){
        alunoService.desmatricular(matricula, codigo);
    }
    
    @PatchMapping("/atualizar/{matricula}")
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable String matricula, AlunoRequestDTO alunoRequestDTO){
        AlunoResponseDTO alunoResponseDTO = alunoService.atualizar(matricula, alunoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(alunoResponseDTO);
    }

    @DeleteMapping("/deletar/{matricula}")
	public ResponseEntity<Void> deletar(@PathVariable String matricula) {
		alunoService.deletar(matricula);
		return ResponseEntity.noContent().build();
	}
}
