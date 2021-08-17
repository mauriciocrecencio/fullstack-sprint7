package br.com.rchlo.store.dto.payment;

import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.domain.PaymentStatus;
import br.com.rchlo.store.repository.PaymentRepository;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PaymentDto {
  private Long id;

  private BigDecimal value;
  private PaymentStatus status;

  public PaymentDto(Long id, BigDecimal value, PaymentStatus status) {
    this.id = id;
    this.value = value;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public BigDecimal getValue() {
    return value;
  }

  public PaymentStatus getStatus() {
    return status;
  }
}
