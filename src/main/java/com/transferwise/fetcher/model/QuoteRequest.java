package com.transferwise.fetcher.model;

import javax.validation.constraints.NotBlank;

public class QuoteRequest {

    public enum Type {
        BALANCE_PAYOUT, BALANCE_CONVERSION, REGULAR
    }

    @NotBlank
    private Long profile;

    @NotBlank
    private String source;

    @NotBlank
    private String target;

    @NotBlank
    private String rateType;

    @NotBlank
    private String targetAmount;

    @NotBlank
    private QuoteRequest.Type type;

    public QuoteRequest() {
    }

    public QuoteRequest(Long profile, String source, String target, String rateType, String targetAmount,
                        QuoteRequest.Type type) {

        this.profile = profile;
        this.source = source;
        this.target = target;
        this.rateType = rateType;
        this.targetAmount = targetAmount;
        this.type = type;
    }

    public Long getProfile() {
        return profile;
    }

    public void setProfile(Long profile) {
        this.profile = profile;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(String targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
