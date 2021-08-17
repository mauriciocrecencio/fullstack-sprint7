package br.com.rchlo.store.controller;

import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.domain.PaymentStatus;
import br.com.rchlo.store.dto.payment.PaymentDto;
import br.com.rchlo.store.dto.payment.PaymentForm;
import br.com.rchlo.store.repository.PaymentRepository;
import java.net.URI;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  private final PaymentRepository paymentRepository;

  public PaymentController(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  @GetMapping("/{id}")
  public ResponseEntity<PaymentDto> paymentById(@PathVariable Long id) {

    Optional<Payment> paymentById = paymentRepository.findById(id);
    if (paymentById.isPresent()) {
      Payment payment = paymentById.get();
      return ResponseEntity
          .ok(new PaymentDto(payment.getId(), payment.getValue(), payment.getStatus()));
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody @Valid PaymentForm form,
      UriComponentsBuilder uriBuilder) {
    Payment payment = form.convert();
    paymentRepository.save(payment);
    URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
    return ResponseEntity.created(uri).body(uri);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updatesPaymentStatusToConfirmed(@PathVariable Long id) {
    Optional<Payment> payment = paymentRepository.findById(id);
    if (payment.isPresent()) {
      try {
        payment.get().updatePaymentStatus(paymentRepository, PaymentStatus.CONFIRMED);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
      } catch (Exception e) {
        return ResponseEntity.badRequest().build();
      }
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> updatesPaymentStatusToCancelled(@PathVariable Long id) {
    Optional<Payment> payment = paymentRepository.findById(id);
    if (payment.isPresent()) {
      try {
        payment.get().updatePaymentStatus(paymentRepository, PaymentStatus.CANCELED);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
      } catch (Exception e) {
        return ResponseEntity.badRequest().build();
      }
    }
    return ResponseEntity.notFound().build();
  }

}
