package crud.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import crud.curso.dto.CursoRequestDTO;
import crud.curso.dto.CursoResponseDTO;
import crud.curso.service.CursoServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
public class CursoController {
    
    @Autowired
    CursoServiceImpl cursoServiceImpl;


    @PostMapping("/cadastrar")
    public ResponseEntity<CursoResponseDTO> cadastrar(@RequestBody @Valid CursoRequestDTO cursoRequestDTO){
        CursoResponseDTO cursoResponseDTO = cursoServiceImpl.cadastrar(cursoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoResponseDTO);
    }

}
