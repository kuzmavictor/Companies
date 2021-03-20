package ua.kharkiv.catalog.dto.deserializer;

/**
 * This exception is thrown if process of deserialization is failed.
 *
 * <p> For example: some JSON token does not exist inside JSON string.
 *
 *
 */
public class JsonDeserializerException extends RuntimeException {
    private static final long serialVersionUID = 0L;

    /**
     * Initializes an exception instance.
     *
     * @param details additional details to describe the problem properly
     */
    public JsonDeserializerException(String details) {
        super(details);
    }
}
