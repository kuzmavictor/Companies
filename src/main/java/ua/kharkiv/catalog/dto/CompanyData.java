package ua.kharkiv.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.errorprone.annotations.Immutable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.checkerframework.checker.nullness.qual.Nullable;
import ua.kharkiv.catalog.dto.deserializer.CompanyDataDeserializer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An object that contains updated data about company from request
 * or fetched data about company from datasource.
 */
@Immutable
@JsonDeserialize(using = CompanyDataDeserializer.class)
public final class CompanyData implements AbstractDto {
    @Nullable
    @ApiModelProperty(position = 0)
    @Getter
    @JsonProperty("id")
    private final String id;

    @ApiModelProperty(position = 1)
    @Getter
    @JsonProperty("name")
    private final String name;

    @ApiModelProperty(position = 2)
    @Getter
    @JsonProperty("title")
    private final String title;

    @ApiModelProperty(position = 3)
    @Getter
    @JsonProperty("founded")
    private final String creationDate;

    /**
     * Creates an instance of {@code UpdatedCompanyData}.
     *
     * @param id           a unique identifier
     * @param name         a company name
     * @param title  a company description
     * @param creationDate a date of creation company
     */
    public CompanyData(@Nullable String id, String name, String title, String creationDate) {
        checkNotNull(name);
        checkNotNull(title);
        checkNotNull(creationDate);


        this.id = id;
        this.name = name;
        this.title = title;
        this.creationDate = creationDate;
    }
}
