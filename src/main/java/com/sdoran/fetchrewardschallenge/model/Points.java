package com.sdoran.fetchrewardschallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Points implements Serializable {

    @Serial
    private static final long serialVersionUID = 3728015878983049135L;

    @JsonProperty("points")
    private Integer points;

    public Points(final Integer points) {
        this.points = points;
    }

    public Points() {
        //default, empty constructor
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
        if (!(o instanceof Points points1)) return false;
        return getPoints().equals(points1.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoints());
    }

    @Override
    public String toString() {
        return "Points{" +
                "points=" + points +
                '}';
    }
}
