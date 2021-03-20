package ua.kharkiv.catalog.given;

/**
 * Provides the keys of JSON data that handles in this application.
 */
public abstract class JsonKeys {

    /**
     * Prevents instantiation of {@code JsonKey}.
     */
    private JsonKeys() {

    }

    public static final String COMPANY_IDENTIFIER = "id";
    public static final String COMPANY_NAME = "name";
    public static final String COMPANY_TITLE = "title";
    public static final String COMPANY_FOUNDED_DATE = "founded";
}
