package com.example_fbf.demo_fbf.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String phoneNumber;
    private String address;
    private List<Long> selectedCartItemIds;
    private String discountCode; // New field to hold the discount code
}
