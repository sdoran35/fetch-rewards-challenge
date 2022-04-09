package com.sdoran.fetchrewardschallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction implements Comparable<Transaction> {

    @JsonProperty("payer")
    private String payer;

    @JsonProperty("points")
    private Integer points;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    public Transaction(final String payer,
                       final Integer points,
                       final LocalDateTime timestamp) {

        this.payer = payer;
        this.points = points;
        this.timestamp = timestamp;
    }

    public Transaction() {
        //default, empty constructor
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(final String payer) {
        this.payer = payer;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(final Integer points) {
        this.points = points;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(final Transaction o) {
        return this.timestamp.compareTo(o.getTimestamp());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return getPayer().equals(that.getPayer()) && getPoints().equals(that.getPoints()) && getTimestamp().equals(that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPayer(), getPoints(), getTimestamp());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "payer='" + payer + '\'' +
                ", points=" + points +
                ", timestamp=" + timestamp +
                '}';
    }
}
