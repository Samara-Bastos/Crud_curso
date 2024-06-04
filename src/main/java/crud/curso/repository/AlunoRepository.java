package crud.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import crud.curso.model.Aluno;
import java.util.Optional;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    Optional<Aluno> findByMatricula(String matricula);

    @Modifying
    @Query(value = "DELETE FROM aluno_cursos WHERE alunos_id = :alunos_id AND cursos_id = :cursos_id", nativeQuery = true)
    void desmatricularAluno(@Param("alunos_id") Integer alunos_id, @Param("cursos_id") Integer cursos_id);

    @Modifying
    @Query(value = "INSERT INTO aluno_cursos (alunos_id, cursos_id) VALUES (:alunos_id, :cursos_id)", nativeQuery = true)
    void matricularAluno(@Param("alunos_id") Integer alunos_id, @Param("cursos_id") Integer cursos_id);
}
