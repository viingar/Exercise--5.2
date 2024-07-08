package com.example.weathermicroservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Weather{
    private int id;
    private String main;
    public String description;
    public String icon;
}
