package com.enamdua.test.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Coordinates {
    private Double latitude;
    private Double longitude;
}
