package com.app.controller;

import com.app.exception.AppException;
import com.app.model.dto.Info;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({AppException.class})
    public Info exceptionInfo(AppException e) {
        return Info.builder().error(e.getMessage()).build();
    }
}
