package br.com.fiap.wastemanagementsystem.advice;

import br.com.fiap.wastemanagementsystem.exception.DataNotFoundException;
import br.com.fiap.wastemanagementsystem.exception.InvalidDataException;
import br.com.fiap.wastemanagementsystem.exception.NotAllowedActionException;
import br.com.fiap.wastemanagementsystem.exception.TokenException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> getMethodArgumentNotValidExceptionMap(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> fields = exception.getBindingResult().getFieldErrors();
        for(FieldError field : fields) {
            errorMap.put(field.getField(), field.getDefaultMessage());
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public Map<String, String> getDataNotFoundExceptionMap(DataNotFoundException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(InvalidDataException.class)
    public Map<String, String> getInvalidDataExceptionMap(InvalidDataException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> getDataIntegrityViolationExceptionMap(DataIntegrityViolationException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TokenException.class)
    public Map<String, String> getTokenExceptionExceptionMap(TokenException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotAllowedActionException.class)
    public Map<String, String> getNotAllowedActionExceptionMap(NotAllowedActionException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }


}
