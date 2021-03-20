package ua.kharkiv.catalog.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.kharkiv.catalog.dto.CompanyData;

import java.io.IOException;
import java.util.Optional;

import static ua.kharkiv.catalog.given.JsonKeys.*;

/**
 * The custom implementation of JSON deserializer for {@link CompanyData} class.
 */
@SuppressWarnings("ConstantConditions") // the {@code JsonNode} element cannot be null
public class CompanyDataDeserializer extends AbstractJsonDeserializer {

    private static final long serialVersionUID = 0L;

    /**
     * {@inheritDoc}
     *
     * <p>Allows to override the deserialization behavior of the request body {@link ResponseBody}
     * for the immutable {@code CompanyData} object.
     */
    @Override
    public CompanyData deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonDeserializerException {
        ErrorHandler errorHandler = new ErrorHandler();

        JsonNode rootNode = p.getCodec().readTree(p);
        JsonNode identifier = rootNode.get(COMPANY_IDENTIFIER);
        JsonNode name = rootNode.get(COMPANY_NAME);
        JsonNode description = rootNode.get(COMPANY_TITLE);
        JsonNode foundedDate = rootNode.get(COMPANY_FOUNDED_DATE);


        if (name == null) {
            String nameErrorMessage = "Cannot obtain the data about the company name.";
            errorHandler.updateErrorMessage(nameErrorMessage);
        }

        if (description == null) {
            String descriptionErrorMessage = "Cannot obtain the data about company description";
            errorHandler.updateErrorMessage(descriptionErrorMessage);
        }

        if (foundedDate == null) {
            String nameErrorMessage = "Cannot obtain the data about the company founded date.";
            errorHandler.updateErrorMessage(nameErrorMessage);
        }

        Optional<String> errors = errorHandler.errors();
        if (errors.isPresent()) {
            throw new JsonDeserializerException(errors.get());
        }

        String companyIdentifier = identifier.asText();
        String companyName = name.asText();
        String companyDescription = description.asText();
        String companyFoundedDate = foundedDate.asText();

        return new CompanyData(companyIdentifier, companyName,
                companyDescription, companyFoundedDate);
    }
}
