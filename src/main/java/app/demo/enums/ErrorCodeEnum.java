package app.demo.enums;

public enum ErrorCodeEnum {
    STUDENT_NOT_FOUND("Student not found");

    private final String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
