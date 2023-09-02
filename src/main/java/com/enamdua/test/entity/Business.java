package com.enamdua.test.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "business")
@Data
public class Business {

    @Id
    private String id;
    private String alias;
    private String name;
    private String imageUrl;
    private Boolean isClosed;
    private String url;
    private Integer reviewCount;
    private Double rating;
    private String price;
    private String phone;
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