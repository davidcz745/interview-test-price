package com.example.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testProductPrice() throws Exception {
        int brandId = 1;
        int productId = 35455;

        testPrice(brandId, productId, "2020-06-14-10.00.00", 35.5F);
        testPrice(brandId, productId, "2020-06-14-16.00.00", 25.45F);
        testPrice(brandId, productId, "2020-06-14-21.00.00", 35.5F);
        testPrice(brandId, productId, "2020-06-15-10.00.00", 30.5F);
        testPrice(brandId, productId, "2020-06-16-21.00.00", 38.95F);
    }

    private void testPrice(int brandId, int productId, String date, float resultPrice) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price/{brandId}/{productId}", brandId, productId)
                        .param("date", date))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(resultPrice));
    }
}