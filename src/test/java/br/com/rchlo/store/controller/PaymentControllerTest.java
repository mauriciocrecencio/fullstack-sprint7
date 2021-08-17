package br.com.rchlo.store.controller;


import br.com.rchlo.store.repository.PaymentRepository;

import java.net.URI;

import mother.PaymentMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/schema.sql")
@ActiveProfiles("test")
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PaymentRepository paymentRepository;


    @BeforeEach
    public void persistPayments() {

        paymentRepository.save(PaymentMother.aCreatedPayment());
        paymentRepository.save(PaymentMother.aCreatedPayment());
        paymentRepository.save(PaymentMother.aConfirmedPayment());
        paymentRepository.save(PaymentMother.aCancelledPayment());
    }

    //  É um test End to End, vai do controller até o banco de dados
    @Test
    void shouldCancelAPayment() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete(new URI("/payments/1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is(201));

    }

    @Test
    void shouldConfirmAPayment() throws Exception {
        URI uri = new URI("/payments/1");
        mockMvc.perform(
                MockMvcRequestBuilders.put(new URI("/payments/1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is(201));


    }

    @Test
    void shouldNotCancelAPayment() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete(new URI("/payments/3"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is(400));

        mockMvc.perform(
                MockMvcRequestBuilders.delete(new URI("/payments/4"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is(400));

    }

    @Test
    void shouldNotConfirmAPayment() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put(new URI("/payments/4"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is(400));

    }

    @Test
    void shouldCreateAPayment() throws Exception {
        String newPayment = "{\"value\": \"123.12\",\"cardClientName\": \"1234567890123456\",\"cardNumber\": 1234567890123456,\"cardExpiration\": \"2022-09\",\"cardVerificationCode\":\"444\"}";
        mockMvc.perform(
                MockMvcRequestBuilders.post(new URI("/payments"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPayment))
                .andExpect(MockMvcResultMatchers.status()
                        .is(201));

    }

    @Test
    void shouldNotCreateAPayment() throws Exception {
        String newPayment = "{\"value\": \"123.12\",\"cardClientName\": \"Nome Legal\",\"cardNumber\": 1a34567890123456,\"cardExpiration\": \"2022-09\",\"cardVerificationCode\":\"444\"}";
        mockMvc.perform(
                MockMvcRequestBuilders.post(new URI("/payments")).contentType(MediaType.APPLICATION_JSON).content(newPayment)).andExpect(MockMvcResultMatchers.status().is(400)).andReturn().getRequest();
    }
}