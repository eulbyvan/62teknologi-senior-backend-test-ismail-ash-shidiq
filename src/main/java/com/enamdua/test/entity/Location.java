package com.enamdua.test.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Location {
    private String address1;
    private String address2;
    private String address3;
    private String city;
    @JsonProperty("zip_code")
    private String zipCode;
    private String country;
    private String state;
}
