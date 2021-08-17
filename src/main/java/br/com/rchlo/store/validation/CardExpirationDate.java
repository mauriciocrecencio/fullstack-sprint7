package br.com.rchlo.store.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CardExpirationDateValidator.class)
public @interface CardExpirationDate {
  String message() default "expiration must be 1 year-month in the future";
  Class<?>[] groups() default {};
  public abstract Class<? extends Payload>[] payload() default {};
}
