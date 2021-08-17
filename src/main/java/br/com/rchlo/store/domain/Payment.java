package br.com.rchlo.store.domain;

import br.com.rchlo.store.repository.PaymentRepository;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal value;

  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  @Embedded
  @AttributeOverride(name = "clientName", column = @Column(name = "card_client_name"))
  @AttributeOverride(name = "number", column = @Column(name = "card_number"))
  @AttributeOverride(name = "expiration", column = @Column(name = "card_expiration"))
  @AttributeOverride(name = "verificationCode", column = @Column(name = "card_verification_code"))
  private Card card;

  public Payment(BigDecimal value, PaymentStatus status, Card card) {
    this.value = value;
    this.status = status;
    this.card = card;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }

  public Payment() {
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

  public Card getCard() {
    return card;
  }

  public boolean canUpdatePaymentStatus(PaymentStatus status) {
    return status.equals(PaymentStatus.CREATED);
  }

  public void updatePaymentStatus(PaymentRepository paymentRepository, PaymentStatus paymentStatus)
      throws IllegalStateException {
    if (canUpdatePaymentStatus(this.status)) {
      setStatus(paymentStatus);
      paymentRepository.save(this);
    } else {
      throw new IllegalStateException("cannot update the PaymentStatus with the status already CONFIRMED or CANCELLED");
    }

  }
}


