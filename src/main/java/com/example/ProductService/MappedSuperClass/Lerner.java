package com.example.ProductService.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;

import java.security.PrivateKey;

@Data
@Entity(name = "msc_Lerner")
public class Lerner extends User{
    private String College;
    private String Company;
}
