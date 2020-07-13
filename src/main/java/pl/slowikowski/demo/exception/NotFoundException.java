package pl.slowikowski.demo.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id, String className) {
        super("Could not find " + className + " with id = " + id);
    }
}
