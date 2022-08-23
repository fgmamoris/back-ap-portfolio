/*
 */
package com.mamoris.portfolio.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Federico Mamoris
 */
@Data
@RequiredArgsConstructor
public class ErrorHandling {

    private final HttpStatus httpStatus;
    private final String message;
}
