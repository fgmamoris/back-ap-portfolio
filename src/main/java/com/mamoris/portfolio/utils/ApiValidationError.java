package com.mamoris.portfolio.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Federico Mamoris
 */
@Data
@EqualsAndHashCode(callSuper = false)

public class ApiValidationError extends ApiSubError {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    ApiValidationError(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

}
