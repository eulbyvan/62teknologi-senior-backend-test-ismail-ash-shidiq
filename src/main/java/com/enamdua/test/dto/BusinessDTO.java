package com.enamdua.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BusinessDTO {
    private String id;
    private String alias;
    private String name;
    private String imageUrl;
    private boolean isClosed;
    private String url;
    private int reviewCount;
    private List<CategoryDTO> categories;
    private double rating;
    private CoordinatesDTO coordinates;
    private List<String> transactions;
    private String price;
    private LocationDTO location;
    private String phone;
    private String displayPhone;
    private double distance;
}