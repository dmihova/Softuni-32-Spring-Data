package su.lab.exceptions;

public class EntityMissingException extends RuntimeException {
    public EntityMissingException(String msg) {
        super(msg);
    }
}