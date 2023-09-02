package com.enamdua.test.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String zipCode;
    private String country;
    private String state;
    private List<String> displayAddress;
}
