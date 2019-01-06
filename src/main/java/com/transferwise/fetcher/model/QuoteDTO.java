package com.transferwise.fetcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class QuoteDTO {

    @NotNull
    @JsonProperty("userId")
    private Long userId;

    @NotNull
    @JsonProperty("paymentAmount")
    private String paymentAmount;

    public QuoteDTO() {
    }

    public QuoteDTO(Long userId, String paymentAmount) {
        this.userId = userId;
        this.paymentAmount = paymentAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

}
