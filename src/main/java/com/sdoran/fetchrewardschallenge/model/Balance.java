package com.sdoran.fetchrewardschallenge.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance implements Serializable {

    @Serial
    private static final long serialVersionUID = 7755923164506753224L;

    @JsonProperty("payerName")
    private String payerName;

    @JsonProperty("balance")
    private Integer balance;

    public Balance(final String payerName,
                   final Integer balance) {

        super();
        this.payerName = payerName;
        this.balance = balance;
    }

    public Balance() {
        //default, empty constructor
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(final String payerName) {
        this.payerName = payerName;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(final Integer balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balance balance1)) return false;
        return getPayerName().equals(balance1.getPayerName()) && getBalance().equals(balance1.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPayerName(), getBalance());
    }

    @Override
    public String toString() {
        return "Balance{" +
                "payerName='" + payerName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
