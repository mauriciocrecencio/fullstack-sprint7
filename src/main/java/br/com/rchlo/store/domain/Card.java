package br.com.rchlo.store.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Card {

    private String clientName;
    private String number;
    private String expiration;
    private String verificationCode;

    public Card(String clientName, String number, String expiration, String verificationCode) {
        this.clientName = clientName;
        this.number = number;
        this.expiration = expiration;
        this.verificationCode = verificationCode;
    }

    public Card() {
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getClientName() {
        return clientName;
    }

    public String getNumber() {
        return number;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}
