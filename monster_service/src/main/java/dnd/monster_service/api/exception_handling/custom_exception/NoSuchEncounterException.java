package dnd.monster_service.api.exception_handling.custom_exception;

public class NoSuchEncounterException extends RuntimeException {
    public NoSuchEncounterException(String message) {
        super(message);
    }


}
