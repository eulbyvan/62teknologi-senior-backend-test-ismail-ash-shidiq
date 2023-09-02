package com.enamdua.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BusinessDTO {
    private UUID id;
    private String alias;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("is_closed")
    private boolean isClosed;
    private String url;
    @JsonProperty("review_count")
    private int reviewCount;
    private List<CategoryDTO> categories;
    private double rating;
    private CoordinatesDTO coordinates;
    private List<String> transactions;
    private String price;
    private LocationDTO location;
    private String phone;
    @JsonProperty("display_phone")
    private String displayPhone;
    private double distance;
}