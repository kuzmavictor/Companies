package ua.kharkiv.catalog.dto.serializer;
/**
 * This exception is thrown if process of serialization is failed.
 *
 * <p> For example: obtained data cannot represent in the current JSON format.
 */
public class JsonSerializationException extends RuntimeException {
    private static final long serialVersionUID = 0L;

    /**
     * Initializes an exception instance.
     *
     * @param details additional details to describe the problem properly
     */
    public JsonSerializationException(String details) {
        super(details);
    }
}
