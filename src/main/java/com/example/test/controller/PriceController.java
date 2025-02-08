package com.example.test.controller;

import com.example.test.model.Price;
import com.example.test.service.PriceService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@RestController
public class PriceController {

    private final PriceService priceService;

    //Formatter for the dates to be received as a parameter
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    // Endpoint that handles GET requests to "/prices"
    @GetMapping("/price/{brandId}/{productId}")
    public Price productPrice(@PathVariable int brandId, @PathVariable int productId, @RequestParam String date) throws ParseException {
        Date parsedDate = formatter.parse(date);
        Optional<Price> price = priceService.getHighestPriorityPrice(brandId, productId, parsedDate, parsedDate);
        return price.orElse(null);
    }
}