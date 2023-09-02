package com.enamdua.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "business")
@Data
public class Business {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @JsonIgnore
    private UUID id;
    private String alias;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("is_closed")
    private Boolean isClosed;
    private String url;
    @JsonProperty("review_count")
    private Integer reviewCount;
    private Double rating;
    private String price;
    private String phone;
    @JsonProperty("display_phone")
    private String displayPhone;
    private Double distance;

    @ToString.Exclude
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    @Embedded
    private Coordinates coordinates;

    @Embedded
    private Location location;
}