package com.example.ktandroid;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductPost implements Serializable {
    String Id;
    String Name;
    int Price;
    String Description;
    String Picture;
    String GroupName;



}
