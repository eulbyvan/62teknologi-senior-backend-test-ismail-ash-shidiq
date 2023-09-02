package com.enamdua.test.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Location {
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String zipCode;
    private String country;
    private String state;
}
