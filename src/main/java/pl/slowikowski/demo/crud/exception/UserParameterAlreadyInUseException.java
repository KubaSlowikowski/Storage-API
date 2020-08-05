package pl.slowikowski.demo.crud.exception;

public class UserParameterAlreadyInUseException extends RuntimeException {
    public UserParameterAlreadyInUseException(String param) {
        super(param + " is already in use.");
    }
}
