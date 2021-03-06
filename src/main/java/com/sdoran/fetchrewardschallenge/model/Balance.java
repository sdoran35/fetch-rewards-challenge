package com.sdoran.fetchrewardschallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance implements Serializable {

    @Serial
    private static final long serialVersionUID = 7755923164506753224L;

    @JsonProperty("payer")
    private String payer;

    @JsonProperty("points")
    private Integer points;

    public Balance(final String payer,
                   final Integer points) {

        super();
        this.payer = payer;
        this.points = points;
    }

    public Balance() {
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Balance balance)) return false;
        return getPayer().equals(balance.getPayer()) && getPoints().equals(balance.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPayer(), getPoints());
    }

    @Override
    public String toString() {
        return "Balance{" +
                "payer='" + payer + '\'' +
                ", points=" + points +
                '}';
    }
}
