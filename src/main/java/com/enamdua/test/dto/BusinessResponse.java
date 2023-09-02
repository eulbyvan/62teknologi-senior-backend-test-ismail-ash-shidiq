package com.enamdua.test.dto;

import lombok.Getter;
import lombok.Setter;

import javax.swing.plaf.synth.Region;
import java.util.List;

@Getter
@Setter
public class BusinessResponse {
    private List<BusinessDTO> businesses;
    private int total;
    private Region region;
}
