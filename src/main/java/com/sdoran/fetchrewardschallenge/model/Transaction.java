package com.sdoran.fetchrewardschallenge.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction implements Comparable<Transaction> {

    @JsonProperty("payerName")
    private String payerName;

    @JsonProperty("balance")
    private Integer balance;

    @JsonProperty("timeStamp")
    private LocalDateTime timeStamp;

    public Transaction(final String payerName,
                       final Integer balance,
                       final LocalDateTime timeStamp) {

        this.payerName = payerName;
        this.balance = balance;
        this.timeStamp = timeStamp;
    }

    public Transaction() {
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

    public LocalDateTime getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(final LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public int compareTo(final Transaction o) {
        return this.timeStamp.compareTo(o.getTimestamp());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return getPayerName().equals(that.getPayerName()) && getBalance().equals(that.getBalance()) && getTimestamp().equals(that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPayerName(), getBalance(), getTimestamp());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "payerName='" + payerName + '\'' +
                ", balance=" + balance +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
