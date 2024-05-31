package crud.curso.exceptions;

public class FindProfessorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FindProfessorException(String ex){
        super(ex);
    }
}
