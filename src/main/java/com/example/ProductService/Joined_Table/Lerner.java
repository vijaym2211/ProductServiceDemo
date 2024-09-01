package com.example.ProductService.Joined_Table;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name= "JT_Lerner")
@PrimaryKeyJoinColumn(name = "user_id")
public class Lerner extends User{
    private String College;
    private String Company;
}
