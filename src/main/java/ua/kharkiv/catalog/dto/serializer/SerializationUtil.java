package ua.kharkiv.catalog.dto.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ua.kharkiv.catalog.dto.CompanyData;
import ua.kharkiv.catalog.dto.ResponseMessage;

@Slf4j
public class SerializationUtil {
    /**
     * Serializes a{@code CompanyData} to JSON.
     *
     * @param companyData a {@code CompanyData} object
     * @return the string in JSON format
     * @throws JsonSerializationException if serialization process is failed
     */
    public static String serializeCompanyData(CompanyData companyData)
            throws JsonSerializationException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(companyData);
        } catch (JsonProcessingException e) {

            if (log.isErrorEnabled()) {
                log.error("Error serialization `CompanyData` object to JSON");
                throw new JsonSerializationException("Cannot represent data in current JSON schema.");
            }
        }
        return jsonString;
    }

    /**
     * Serializes a list of {@code CompanyData} to JSON.
     *
     * @param companiesDataList a list of the {@code CompanyData} objects
     * @return the string in JSON format
     * @throws JsonSerializationException if serialization process is failed
     */
    public static String serializeListCompaniesData(Iterable<CompanyData> companiesDataList)
            throws JsonSerializationException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;

        try {
            jsonString = objectMapper.writeValueAsString(companiesDataList);
        } catch (JsonProcessingException e) {

            if (log.isErrorEnabled()) {
                log.error("Error serialization a list of data about companies to JSON");
                throw new JsonSerializationException("Cannot represent data in current JSON schema.");
            }
        }

        return jsonString;
    }

    /**
     * Serializes a {@code ResponseMessage} to JSON.
     *
     * @param responseMessage a {@code ResponseMessage} object
     * @return the string in JSON format
     * @throws JsonSerializationException
     */
    public static String serializeResponseMessage(ResponseMessage responseMessage)
            throws JsonSerializationException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;

        try {
            jsonString = objectMapper.writeValueAsString(responseMessage);
        } catch (JsonProcessingException e) {

            if (log.isErrorEnabled()) {
                log.error("Error serilaization a response message to JSON.");
                throw new JsonSerializationException("Cannot represent data from response message");
            }
        }
        return jsonString;
    }
}
