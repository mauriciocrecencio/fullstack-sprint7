package br.com.rchlo.store.dto.payment;

import br.com.rchlo.store.domain.Card;
import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.domain.PaymentStatus;
import br.com.rchlo.store.validation.CardExpirationDate;
import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class PaymentForm {

  @NotNull
  @Positive
  @Digits(integer = Integer.MAX_VALUE, fraction = 2)
  private BigDecimal value;

  @NotEmpty(message = "cardClientName is required")
  @Length(max = 100)
  private String cardClientName;

  @NotEmpty(message = "cardNumber is required")
//  O regex já verifica o Size de 16, mas preferi deixar separado
//  a verificacao de Somente Numeros e tamanho para ficar mais claro ao usuário sobre a msg
  @Pattern(regexp = "^[0-9]*", message = "must contains only numbers")
  @Pattern(regexp = "^[0-9]{16}", message = "size must be 16")
  private String cardNumber;

  @Pattern(regexp = "^[0-9]{4}-[0-9]{2}", message = "must be in the pattern YYYY-mm")
  @CardExpirationDate
  private String cardExpiration;

  @NotNull
//  Mesmo coisa do cardnumber
  @Pattern(regexp = "^[0-9]*", message = "must contains only numbers")
  @Pattern(regexp = "^[0-9]{3}", message = "size must be 3")
  private String cardVerificationCode;

  private PaymentStatus status = PaymentStatus.CREATED;

  public Payment convert() {
    Card card = new Card(cardClientName, cardNumber, cardExpiration, cardVerificationCode);
    return new Payment(value, status, card);
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public String getCardClientName() {
    return cardClientName;
  }

  public void setCardClientName(String cardClientName) {
    this.cardClientName = cardClientName;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardExpiration() {
    return cardExpiration;
  }

  public void setCardExpiration(String cardExpiration) {
    this.cardExpiration = cardExpiration;
  }

  public String getCardVerificationCode() {
    return cardVerificationCode;
  }

  public void setCardVerificationCode(String cardVerificationCode) {
    this.cardVerificationCode = cardVerificationCode;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }
}
