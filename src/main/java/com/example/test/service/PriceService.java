package com.example.test.service;

import com.example.test.model.Price;
import com.example.test.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    // Method to get the price with the highest priority within the query results
    public Optional<Price> getHighestPriorityPrice(int productId, int brandId, Date startDate, Date endDate) {
        List<Price> prices = priceRepository.findPriceOrderByPriority(productId, brandId, startDate, endDate);
        return prices.stream().findFirst();
    }
}