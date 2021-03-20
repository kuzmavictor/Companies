package ua.kharkiv.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.errorprone.annotations.Immutable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An object that contains response message.
 */
@Immutable
public final class ResponseMessage implements AbstractDto {

    @Getter
    @ApiModelProperty(position = 0)
    @JsonProperty("message")
    private final String responseMessage;

    /**
     * Creates an instance of {@code ResponseMessage}
     *
     * @param responseMessage a response message
     */
    public ResponseMessage(String responseMessage) {
        checkNotNull(responseMessage);

        this.responseMessage = responseMessage;
    }
}
