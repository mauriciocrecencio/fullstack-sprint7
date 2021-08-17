package br.com.rchlo.store.validation;

import java.util.Calendar;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class CardExpirationDateValidator implements
    ConstraintValidator<CardExpirationDate, String> {

  @Override
  public void initialize(CardExpirationDate constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
    String[] dateSplit = date.split("-");

    int cardExpirationYear = Integer.parseInt(dateSplit[0]);
    int cardExpirationMonth = Integer.parseInt(dateSplit[1]);

    Calendar targetDate = Calendar.getInstance();
    Calendar currentDate = Calendar.getInstance();
    targetDate.set(Calendar.YEAR, cardExpirationYear);
    targetDate.set(Calendar.MONTH, cardExpirationMonth);
    return targetDate.after(currentDate);

  }
}
