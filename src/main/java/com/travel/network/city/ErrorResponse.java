package com.travel.network.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Error Model")
public class ErrorResponse {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Error code", name = "code", value = "200")
    private int code;

    @ApiModelProperty(notes = "Status", name = "status", value = "SUCCESS")
    private int status;

    @ApiModelProperty(notes = "Message", name = "message", value = "Invalid")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
