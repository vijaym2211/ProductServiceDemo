package com.example.ProductService.Table_Per_Class;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "TPC_User")
public class User {
    @Id
    private int user_id;
    private String name;
    private String email;
    private String password;
}
