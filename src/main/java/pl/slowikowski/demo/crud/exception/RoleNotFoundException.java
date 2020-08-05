package pl.slowikowski.demo.crud.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        super("Could not find any role.");
    }

    public RoleNotFoundException(String role) {
        super("Role: " + role + " is not found.");
    }
}
