package com.dev.tenisuatelier.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {
    private int status;
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
