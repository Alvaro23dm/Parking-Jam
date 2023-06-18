package es.upm.pproject.parkingjam.exceptions;

/**
 * Exception that is thrown when a level is not found.
 * @author Nihel Kella Bouziane
 * @since 15/06/2023
 * @version 1.0
 */
public class LevelNotFoundException extends Exception {
    public LevelNotFoundException(String message) {
        super(message);
    }
}