package com.example.ProductService.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "msc_TA")
public class TA extends User{
    private int Num_of_que;
    private String College;
}
