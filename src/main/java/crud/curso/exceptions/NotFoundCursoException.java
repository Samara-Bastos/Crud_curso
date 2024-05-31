package crud.curso.exceptions;

public class NotFoundCursoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundCursoException(String ex){
        super(ex);
    }
}
