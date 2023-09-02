package com.enamdua.test.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String alias;
    private String title;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;
}