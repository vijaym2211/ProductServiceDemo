package com.example.ProductService.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "msc_mentor")
public class Mentor extends User{
    private double Avg_rating;
    private String Current_company;

}
