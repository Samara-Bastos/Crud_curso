package crud.curso.exceptions;

public class FindAlunoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public FindAlunoException(String ex){
        super(ex);
    }

}
