package crud.curso.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Aluno {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Column(nullable = false, length = 60)
    private String nome;

    @Setter
    @Column(nullable = false, unique = true, length = 8)
    private String matricula;

    @Setter
    @ManyToMany
    @JsonIgnore
    private List<Curso> cursos;
    

    public Aluno(String nome, String matricula){
        this.nome = nome;
        this.matricula = matricula;
    }
}
