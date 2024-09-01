package com.example.ProductService.Table_Per_Class;

import com.example.ProductService.Joined_Table.User;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name= "TPC_Lerner")
public class Lerner extends User {
    private String College;
    private String Company;
}
