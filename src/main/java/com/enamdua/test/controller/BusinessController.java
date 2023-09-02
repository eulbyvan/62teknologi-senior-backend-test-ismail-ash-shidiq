package com.enamdua.test.controller;

import com.enamdua.test.dto.BusinessResponse;
import com.enamdua.test.entity.Business;
import com.enamdua.test.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v3/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping("/search")
    public ResponseEntity<BusinessResponse> searchBusinesses(
                                                               @RequestParam(required = false) String term,
                                                               @RequestParam(required = false) String location,
                                                               @RequestParam(required = false) Double latitude,
                                                               @RequestParam(required = false) Double longitude,
                                                               @RequestParam(required = false) Double radius,
                                                               @RequestParam(required = false) List<String> categories,
                                                               @RequestParam(required = false) String locale,
                                                               @RequestParam(defaultValue = "10") int limit,
                                                               @RequestParam(defaultValue = "0") int offset,
                                                               @RequestParam(required = false) String sortBy,
                                                               @RequestParam(required = false) String price,
                                                               @RequestParam(required = false) Boolean openNow,
                                                               @RequestParam(required = false) String openAt,
                                                               @RequestParam(required = false) String attributes
    ) {

        BusinessResponse response = businessService.searchBusinesses(
                term, location, latitude, longitude, radius, categories, locale, limit, offset, sortBy, price, openNow, openAt, attributes
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Business> addBusiness(@RequestBody Business business) {
        Business savedBusiness = businessService.saveBusiness(business);
        return new ResponseEntity<>(savedBusiness, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Business> updateBusiness(@RequestBody Business business) {
        Business updatedBusiness = businessService.updateBusiness(business);
        return new ResponseEntity<>(updatedBusiness, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable String id) {
        businessService.deleteBusiness(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}