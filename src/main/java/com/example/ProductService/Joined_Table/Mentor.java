package com.example.ProductService.Joined_Table;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name="JT_Mentor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User{
    private double Avg_rating;
    private String Current_company;
}
