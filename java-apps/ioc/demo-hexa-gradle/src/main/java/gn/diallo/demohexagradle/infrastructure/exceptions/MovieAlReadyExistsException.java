package gn.diallo.demohexagradle.infrastructure.exceptions;

public class MovieAlReadyExistsException extends RuntimeException {
    public MovieAlReadyExistsException(String message){
        super(message);
    }
}
