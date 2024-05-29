package crud.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import crud.curso.model.Curso;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    Optional<Curso> findByCodigo(String codigo);
}
