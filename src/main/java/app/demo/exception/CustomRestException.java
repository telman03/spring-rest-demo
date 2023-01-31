package app.demo.exception;

public class CustomRestException extends RuntimeException{
    public CustomRestException(String message) {
        super(message);
    }
}
