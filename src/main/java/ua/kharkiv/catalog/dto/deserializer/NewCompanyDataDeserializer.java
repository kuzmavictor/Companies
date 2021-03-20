package ua.kharkiv.catalog.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.kharkiv.catalog.dto.NewCompanyData;

import java.io.IOException;
import java.util.Optional;

import static ua.kharkiv.catalog.given.JsonKeys.COMPANY_TITLE;
import static ua.kharkiv.catalog.given.JsonKeys.COMPANY_NAME;

/**
 * The custom implementation of JSON deserializer for {@link NewCompanyData} class.
 */
@SuppressWarnings("ConstantConditions") // the {@code JsonNode} element cannot be null
public class NewCompanyDataDeserializer extends AbstractJsonDeserializer {

    private static final long serialVersionUID = 0L;

    /**
     * {@inheritDoc}
     *
     * <p>Allows to override the deserialization behavior of the request body {@link ResponseBody}
     * for the immutable {@code NewCompanyData} object.
     */
    @Override
    public NewCompanyData deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonDeserializerException {
        ErrorHandler errorHandler = new ErrorHandler();
        JsonNode rootNode = p.getCodec().readTree(p);

        JsonNode name = rootNode.get(COMPANY_NAME);
        JsonNode description = rootNode.get(COMPANY_TITLE);

        if (name == null) {
            String nameErrorMessage = "Cannot obtain the data about the company name.";
            errorHandler.updateErrorMessage(nameErrorMessage);
        }

        if (description == null) {
            String descriptionErrorMessage = "Cannot obtain the data about company description";
            errorHandler.updateErrorMessage(descriptionErrorMessage);
        }

        Optional<String> errors = errorHandler.errors();
        if (errors.isPresent()) {
            throw new JsonDeserializerException(errors.get());
        }

        String companyName = name.asText();
        String companyDescription = description.asText();

        return new NewCompanyData(companyName, companyDescription);
    }
}
