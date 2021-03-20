package ua.kharkiv.catalog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.kharkiv.catalog.dto.ResponseMessage;
import ua.kharkiv.catalog.dto.deserializer.JsonDeserializerException;
import ua.kharkiv.catalog.dto.serializer.JsonSerializationException;
import ua.kharkiv.catalog.dto.serializer.SerializationUtil;
import ua.kharkiv.catalog.service.CatalogOperationException;

/**
 * A specified endpoint that centralized handles exception in this application.
 * <p> Allows provides to client-side more readability responses
 * about errors with necessary HTTP codes.
 */
@ControllerAdvice
public class CompanyExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Returns to client the message and code error
     * when {@code JsonDeserializerException} is occurred.
     *
     * @param exception an object of {@code JsonDeserializerException}
     * @return the {@code ResponseEntity} with given HTTP code and body
     */
    @ExceptionHandler(value = JsonDeserializerException.class)
    public ResponseEntity<?> handleCreateDtoException(JsonDeserializerException exception) {
        ResponseMessage responseMessage = new ResponseMessage(exception.getMessage());
        String json = SerializationUtil.serializeResponseMessage(responseMessage);

        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    /**
     * Returns to client the message and code error
     * when {@code CatalogOperationException} is occurred.
     *
     * @param exception an object of {@code CatalogOperationException}
     * @return the {@code ResponseEntity} with given HTTP code and body
     */
    @ExceptionHandler(value = CatalogOperationException.class)
    public ResponseEntity<?> handleCatalogOperationException(CatalogOperationException exception) {
        ResponseMessage responseMessage = new ResponseMessage(exception.getMessage());
        String json = SerializationUtil.serializeResponseMessage(responseMessage);

        return new ResponseEntity<>(json, exception.getErrorCode());
    }

    /**
     * Returns to client the message and code error
     * when {@code JsonSerializationException} is occurred.
     *
     * @param exception an object of {@code JsonSerializationException}
     * @return the {@code ResponseEntity} with given HTTP code and body
     */
    @ExceptionHandler(value = JsonSerializationException.class)
    public ResponseEntity<?> handleJsonOperationException(JsonSerializationException exception) {
        ResponseMessage responseMessage = new ResponseMessage(exception.getMessage());
        String json = SerializationUtil.serializeResponseMessage(responseMessage);

        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
