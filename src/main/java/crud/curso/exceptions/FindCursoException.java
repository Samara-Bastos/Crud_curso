package crud.curso.exceptions;

public class FindCursoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FindCursoException(String ex){
        super(ex);
    }
}
