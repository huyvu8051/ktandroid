package com.example.ktandroid;

import java.util.List;

import lombok.Data;

@Data
public class GetResponseDto {

    private boolean status;
    private List<ProductGet> data;
}
