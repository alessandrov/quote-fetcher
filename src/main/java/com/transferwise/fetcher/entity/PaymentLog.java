package com.transferwise.fetcher.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "payment_log")
@EntityListeners(AuditingEntityListener.class)
public class PaymentLog {

    @Id
    private Long id;

    @NotBlank
    @Column(name = "source", nullable = false)
    private String source;

    @NotBlank
    @Column(name = "target", nullable = false)
    private String target;

    @NotNull
    @Column(name = "sourceAmount", precision = 12, scale = 2, nullable = false)
    private BigDecimal sourceAmount;

    @NotNull
    @Column(name = "targetAmount", precision = 12, scale = 2, nullable = false)
    private BigDecimal targetAmount;

    @NotNull
    @Column(name = "rate", scale = 4, nullable = false)
    private BigDecimal rate;

    @NotNull
    @Column(name = "fee", precision = 12, scale = 2, nullable = false)
    private BigDecimal fee;

    public PaymentLog(){
    }

    public PaymentLog(Long id, String source, String target, BigDecimal sourceAmount, BigDecimal targetAmount,
                      BigDecimal rate, BigDecimal fee){

        this.id = id;
        this.source = source;
        this.target = target;
        this.sourceAmount = sourceAmount;
        this.targetAmount = targetAmount;
        this.rate = rate;
        this.fee = fee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigDecimal getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(BigDecimal sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

}
