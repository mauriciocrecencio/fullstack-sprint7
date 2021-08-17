package br.com.rchlo.store.config.validation;

import br.com.rchlo.store.config.validation.ErrorDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationError {

  @Autowired
  private MessageSource messageSource;

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public List<ErrorDto> handle(MethodArgumentNotValidException exception) {
//        Dentro do excpetion, tem todos os erros que aconteceram
    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
    return fieldErrors.stream().map(
        e -> new ErrorDto(e.getField(), messageSource.getMessage(e,
            LocaleContextHolder.getLocale()))).collect(Collectors.toList());
  }
}
