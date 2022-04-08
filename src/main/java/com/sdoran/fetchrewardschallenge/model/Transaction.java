package com.sdoran.fetchrewardschallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction implements Comparable<Transaction> {

    @JsonProperty("payerName")
    private String payerName;

    @JsonProperty("points")
    private Integer points;

    @JsonProperty("timeStamp")
    private LocalDateTime timeStamp;

    public Transaction(final String payerName,
                       final Integer points,
                       final LocalDateTime timeStamp) {

        this.payerName = payerName;
        this.points = points;
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(final Integer points) {
        this.points = points;
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
        return getPayerName().equals(that.getPayerName()) && getPoints().equals(that.getPoints()) && getTimestamp().equals(that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPayerName(), getPoints(), getTimestamp());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "payerName='" + payerName + '\'' +
                ", points=" + points +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
