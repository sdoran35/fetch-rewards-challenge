package com.sdoran.fetchrewardschallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpentBalance extends Balance {

    @Serial
    private static final long serialVersionUID = 5506720827263260971L;

    @JsonProperty("timeStamp")
    private LocalDateTime timeStamp;

    public SpentBalance(final String payerName,
                        final Integer balance,
                        final LocalDateTime timeStamp) {

        super(payerName, balance);
        this.timeStamp = timeStamp;
    }

    public SpentBalance() {
        //default, empty constructor
    }

    public LocalDateTime getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(final LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpentBalance that)) return false;
        if (!super.equals(o)) return false;
        return getTimestamp().equals(that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTimestamp());
    }

    @Override
    public String toString() {
        return "SpentBalance{" +
                "timeStamp=" + timeStamp +
                '}';
    }
}
