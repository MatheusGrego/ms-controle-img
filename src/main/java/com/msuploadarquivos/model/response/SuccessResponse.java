package com.msuploadarquivos.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse {

    private int status;
    private String message;

    public SuccessResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}