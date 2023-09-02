package com.enamdua.test.service;

import com.enamdua.test.dto.*;
import com.enamdua.test.entity.Business;
import com.enamdua.test.entity.Category;
import com.enamdua.test.repository.BusinessRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    private Specification<Business> buildSpecification(
            String term,
            String location,
            Double latitude,
            Double longitude,
            Double radius,
            List<String> categories,
            String locale,
            int limit,
            int offset,
            String sortBy,
            String price,
            Boolean openNow,
            String openAt,
            String attributes) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (term != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + term + "%"));
            }

            if (location != null) {
                predicates.add(criteriaBuilder.equal(root.get("location").get("state"), location));
            }

            if (latitude != null && longitude != null && radius != null) {
                predicates.add(criteriaBuilder.between(root.get("coordinates").get("latitude"), latitude - radius, latitude + radius));
                predicates.add(criteriaBuilder.between(root.get("coordinates").get("longitude"), longitude - radius, longitude + radius));
            }

            if (categories != null && !categories.isEmpty()) {
                jakarta.persistence.criteria.Join<Business, Category> categoryJoin = root.join("categories");
                predicates.add(categoryJoin.get("alias").in(categories));
            }

            if (price != null) {
                predicates.add(criteriaBuilder.equal(root.get("price"), price));
            }

            // Sorting logic
            if ("best_match".equals(sortBy)) {
                query.orderBy(criteriaBuilder.desc(root.get("rating")), criteriaBuilder.desc(root.get("reviewCount")));
            } else if ("rating".equals(sortBy)) {
                query.orderBy(criteriaBuilder.desc(root.get("rating")));
            } else if ("reviewCount".equals(sortBy)) {
                query.orderBy(criteriaBuilder.desc(root.get("reviewCount")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public BusinessResponse searchBusinesses(
            String term,
            String location,
            Double latitude,
            Double longitude,
            Double radius,
            List<String> categories,
            String locale,
            int limit,
            int offset,
            String sortBy,
            String price,
            Boolean openNow,
            String openAt,
            String attributes) {

        Pageable paging = PageRequest.of(offset, limit);

        Specification<Business> spec = buildSpecification(
                term, location, latitude, longitude, radius, categories, locale, limit, offset, sortBy, price, openNow, openAt, attributes
        );

        Page<Business> pagedResult = businessRepository.findAll(spec, paging);

        List<Business> businesses = pagedResult.getContent();
        List<BusinessDTO> businessDTOs = businesses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        BusinessResponse response = new BusinessResponse();
        response.setBusinesses(businessDTOs);
        response.setTotal(pagedResult.getTotalElements());

        return response;
    }

    private BusinessDTO convertToDto(Business business) {
        BusinessDTO dto = new BusinessDTO();
        dto.setId(business.getId());
        dto.setAlias(business.getAlias());
        dto.setName(business.getName());
        dto.setImageUrl(business.getImageUrl());
        dto.setClosed(business.getIsClosed());
        dto.setUrl(business.getUrl());
        dto.setReviewCount(business.getReviewCount());
        dto.setRating(business.getRating());
        dto.setPhone(business.getPhone());
        dto.setDisplayPhone(business.getDisplayPhone());
        dto.setDistance(business.getDistance());
        dto.setPrice(business.getPrice());

        // Convert categories
        List<CategoryDTO> categoryDTOs = business.getCategories().stream()
                .map(this::convertCategoryToDto)
                .collect(Collectors.toList());
        dto.setCategories(categoryDTOs);

        // Convert coordinates
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setLatitude(business.getCoordinates().getLatitude());
        coordinatesDTO.setLongitude(business.getCoordinates().getLongitude());
        dto.setCoordinates(coordinatesDTO);

        // Convert location
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setAddress1(business.getLocation().getAddress1());
        locationDTO.setAddress2(business.getLocation().getAddress2());
        // ... (and so on for other fields)
        dto.setLocation(locationDTO);

        return dto;
    }

    private CategoryDTO convertCategoryToDto(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setAlias(category.getAlias());
        dto.setTitle(category.getTitle());
        return dto;
    }

    public Business saveBusiness(Business business) {
        return businessRepository.save(business);
    }

    public Business updateBusiness(Business business) {
        return businessRepository.save(business);
    }

    public void deleteBusiness(String id) {
        businessRepository.deleteById(id);
    }
}