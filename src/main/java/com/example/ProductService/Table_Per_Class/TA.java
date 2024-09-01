package com.example.ProductService.Table_Per_Class;

import com.example.ProductService.Joined_Table.User;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "TPC_TA")

public class TA extends User {
    private int Num_of_que;
    private String College;
}
