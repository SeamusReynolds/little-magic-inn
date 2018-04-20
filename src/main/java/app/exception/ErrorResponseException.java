package app.exception;

public class ErrorResponseException extends RuntimeException {
    private int statusCode;
    
    public ErrorResponseException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    
    public ErrorResponseException(String message) {
        this(message, 206);
    }
    
    public int getStatusCode() {
        return statusCode;
    }
}
