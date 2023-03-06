package com.albgott.catalogueservice.shared.infrastructure.exception;

import com.albgott.catalogueservice.shared.domain.exception.PackageException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            PackageException.class
    })
    @ResponseBody
    public List<String> badRequest(HttpServletRequest request, PackageException exception) {
        return exception.errors();
    }

}
