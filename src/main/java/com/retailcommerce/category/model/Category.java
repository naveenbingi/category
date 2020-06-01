package com.retailcommerce.category.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    private String name;
    private int code;
    private String description;
    private String imageURL;
}
