package com.example.ktandroid;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductGet implements Serializable {
    private String id;
    private String name;
    private int price;
    private String description;
    private String picture;
    private String groupName;
}
