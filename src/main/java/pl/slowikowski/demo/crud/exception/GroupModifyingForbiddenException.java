package pl.slowikowski.demo.crud.exception;

public class GroupModifyingForbiddenException extends RuntimeException {
    public GroupModifyingForbiddenException(){
        super("Cannot modify this group. Required for the system to function properly.");
    }
}
