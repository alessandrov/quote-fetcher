package com.transferwise.fetcher.model;

public class QuoteResponse {

    private String id;

    private String source;

    private String type;

    private String createdTime;

    private String rateType;

    private String deliveryEstimate;

    private boolean guaranteedTargetAmount;

    private boolean ofSourceAmount;

    private String[] allowedProfileTypes;

    private String createdByUserId;

    private String profile;

    private String business;

    private String target;

    private String sourceAmount;

    private String targetAmount;

    private String rate;

    private String fee;

    public QuoteResponse() {
    }

    public QuoteResponse(String id, String source, String target, String sourceAmount, String targetAmount,
                         String rate, String fee) {

        this.id = id;
        this.source = source;
        this.target = target;
        this.sourceAmount = sourceAmount;
        this.targetAmount = targetAmount;
        this.rate = rate;
        this.fee = fee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getDeliveryEstimate() {
        return deliveryEstimate;
    }

    public void setDeliveryEstimate(String deliveryEstimate) {
        this.deliveryEstimate = deliveryEstimate;
    }

    public boolean isGuaranteedTargetAmount() {
        return guaranteedTargetAmount;
    }

    public void setGuaranteedTargetAmount(boolean guaranteedTargetAmount) {
        this.guaranteedTargetAmount = guaranteedTargetAmount;
    }

    public boolean isOfSourceAmount() {
        return ofSourceAmount;
    }

    public void setOfSourceAmount(boolean ofSourceAmount) {
        this.ofSourceAmount = ofSourceAmount;
    }

    public String[] getAllowedProfileTypes() {
        return allowedProfileTypes;
    }

    public void setAllowedProfileTypes(String[] allowedProfileTypes) {
        this.allowedProfileTypes = allowedProfileTypes;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(String sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public String getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(String targetAmount) {
        this.targetAmount = targetAmount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
