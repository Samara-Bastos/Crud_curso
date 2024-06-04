package crud.curso.exceptions;

public class NotFoundAlunoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NotFoundAlunoException(String ex){
        super(ex);
    }
}
