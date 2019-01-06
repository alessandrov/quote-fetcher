package com.transferwise.fetcher.exception;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;

/**
 *
 * Unused alternative error handling strategy
 *
 */
@Order(-2)
public class GlobalErrorWebExceptionHandler extends
        DefaultErrorWebExceptionHandler {

    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                          ResourceProperties resourceProperties,
                                          ErrorProperties errorProperties,
                                          ApplicationContext applicationContext) {

        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

}