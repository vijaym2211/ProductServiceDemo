package com.example.ProductService.Table_Per_Class;

import com.example.ProductService.Joined_Table.User;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name="TPC_Mentor")
public class Mentor extends User {
    private double Avg_rating;
    private String Current_company;
}
