package nl.smartworkx.admin.infrastructure.validation;

/**
 * Created by joris on 1-6-17.
 */
public class ValidationError {
    private final String key;
    private String message;

    public ValidationError(String key, String message) {

        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }
}
