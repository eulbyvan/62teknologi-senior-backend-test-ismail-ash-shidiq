package com.enamdua.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BusinessResponse {
    private List<BusinessDTO> businesses;
    private long total;
    private Region region;
}
