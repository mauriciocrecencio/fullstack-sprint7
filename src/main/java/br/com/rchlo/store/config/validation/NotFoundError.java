package br.com.rchlo.store.config.validation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class NotFoundError {

  @Autowired
  private MessageSource messageSource;

  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public List<ErrorDto> handle(MethodArgumentNotValidException exception) {
    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
    return fieldErrors.stream().map(error -> new ErrorDto(error.getField(),
        messageSource.getMessage(error, LocaleContextHolder.getLocale())))
                      .collect(Collectors.toList());
  }

}
