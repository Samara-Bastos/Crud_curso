package crud.curso.exceptions;

public class NotFoundProfessorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundProfessorException(String ex){
        super(ex);
    }
}
