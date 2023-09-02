package com.enamdua.test.repository;

import com.enamdua.test.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BusinessRepository extends JpaRepository<Business, String>, JpaSpecificationExecutor<Business> {
}
