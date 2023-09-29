package dnd.RestApi.exception.custom_exception;

public class NoSuchEncounterException extends RuntimeException {
    public NoSuchEncounterException(String message) {
        super(message);
    }


}
