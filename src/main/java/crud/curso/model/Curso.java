package crud.curso.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Curso {
    
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Column(nullable = false, length = 60)
    private String nome;

    @Setter
    @Column(nullable = false, unique = true, length = 4)
    private String codigo;

    @Setter
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "professor_id", referencedColumnName = "registro")
    private Professor professor;

    @ManyToMany(mappedBy = "cursos")
    private List<Aluno> alunos;

}
