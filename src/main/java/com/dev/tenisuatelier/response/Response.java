package com.dev.tenisuatelier.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {
    private int status;
    private Object data;
    private String message;
}
