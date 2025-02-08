package com.example.test.repository;

import com.example.test.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    /* Query to retrieve prices based on brand id, product id and date */
    @Query("SELECT p FROM Price p WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND p.startDate <= :endDate " +
            "AND p.endDate >= :startDate " +
            "ORDER BY p.priority DESC")
    List<Price> findPriceOrderByPriority(
            @Param("brandId") int brandId,
            @Param("productId") int productId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}