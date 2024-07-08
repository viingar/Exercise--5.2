package com.example.weathermicroservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sys{
    public String country;
    public int sunrise;
    public int sunset;
}