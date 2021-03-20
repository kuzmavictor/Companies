package ua.kharkiv.catalog.service;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This exception is thrown if any operation with companies is failed.
 *
 * <p> For example:
 * <ol>
 * <li> the failure to create a new company;
 * <li> the failure to update information of company;
 * <li> the failure to delete information about company;
 * <li> the failure to get information about company or companies.
 * </ol>
 */
@Getter
public class CatalogOperationException extends RuntimeException {
    private static final long serialVersionUID = 0L;
    private final HttpStatus errorCode;

    /**
     * Initializes an exception.
     *
     * @param details   additional details to describe the problem properly
     * @param errorCode a code of error {@link HttpStatus}
     */

    public CatalogOperationException(String details, HttpStatus errorCode) {
        super(details);
        this.errorCode = errorCode;
    }
}
