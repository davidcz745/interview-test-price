package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Prices")
public class Price {

    @JsonProperty("brandId")
    private int brandId;
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;
    @Id
    @JsonProperty("priceList")
    private int priceList;
    @JsonProperty("productId")
    private int productId;
    private int priority;
    @JsonProperty("price")
    private float price;
    @JsonProperty("curr")
    private String curr;

}
