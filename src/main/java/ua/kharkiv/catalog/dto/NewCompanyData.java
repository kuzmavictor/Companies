package ua.kharkiv.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.errorprone.annotations.Immutable;
import lombok.Getter;
import ua.kharkiv.catalog.dto.deserializer.NewCompanyDataDeserializer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An object that contains data about new company.
 */
@Immutable
@JsonDeserialize(using = NewCompanyDataDeserializer.class)
public final class NewCompanyData implements AbstractDto {

    @Getter
    @JsonProperty("name")
    private final String companyName;

    @Getter
    @JsonProperty("title")
    private final String title;

    /**
     * Creates an instance of {@code NewCompanyData}
     *
     * @param companyName
     *         a company name
     * @param title
     *         a company description
     */

    public NewCompanyData(String companyName, String title) {
        checkNotNull(companyName);
        checkNotNull(title);

        this.companyName = companyName;
        this.title = title;
    }
}
