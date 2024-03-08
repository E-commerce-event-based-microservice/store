package com.group16.common.domain;

import com.group16.common.exception.CommonException;
import lombok.Data;

/**
 * Generic response wrapper used for standardizing the API response structure.
 */
@Data
public class R<T> {
    private int code; // HTTP status code or custom response code.
    private String msg; // Response message or error description.
    private T data; // Data payload of the response.

    // Creates a success response with no data.
    public static R<Void> ok() {
        return ok(null);
    }

    // Creates a success response with data.
    public static <T> R<T> ok(T data) {
        return new R<>(200, "OK", data);
    }

    // Creates an error response with a default error code and custom message.
    public static <T> R<T> error(String msg) {
        return new R<>(500, msg, null);
    }

    // Creates an error response with a custom error code and message.
    public static <T> R<T> error(int code, String msg) {
        return new R<>(code, msg, null);
    }

    // Creates an error response based on a CommonException instance.
    public static <T> R<T> error(CommonException e) {
        return new R<>(e.getCode(), e.getMessage(), null);
    }

    // Default constructor.
    public R() {
    }

    // Constructor initializing all fields.
    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // Checks if the response represents a success.
    public boolean success(){
        return code == 200;
    }
}
