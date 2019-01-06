package com.transferwise.fetcher.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @NotBlank
    @Column(name = "payoutCurrency", nullable = false)
    private String payoutCurrency;

    public User() {
    }

    public User(String firstName, String lastName, String payoutCurrency) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.payoutCurrency = payoutCurrency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPayoutCurrency() {
        return payoutCurrency;
    }

    public void setPayoutCurrency(String payoutCurrency) {
        this.payoutCurrency = payoutCurrency;
    }

}
