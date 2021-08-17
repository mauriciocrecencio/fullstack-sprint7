package mother;

import br.com.rchlo.store.domain.Card;
import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.domain.PaymentStatus;
import java.math.BigDecimal;

public class PaymentMother {

  public static Payment aCreatedPayment() {
    Card card = new Card("Carlita Rosa", "1234567890123456", "2022-04", "123");
    return new Payment(new BigDecimal("4123.10"), PaymentStatus.CREATED, card);
  }
  public static Payment aConfirmedPayment() {
    Card card = new Card("Jouse Balboa", "6543210987654321", "2023-02", "321");
    return new Payment(new BigDecimal("3.10"), PaymentStatus.CONFIRMED, card);
  }
  public static Payment aCancelledPayment() {
    Card card = new Card("Fernanda Cosa", "1223334444555550", "2023-01", "333");
    return new Payment(new BigDecimal("11123.10"), PaymentStatus.CANCELED, card);
  }

}
