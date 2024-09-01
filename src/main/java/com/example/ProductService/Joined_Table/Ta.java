package com.example.ProductService.Joined_Table;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name = "JT_TA")
@PrimaryKeyJoinColumn(name = "user_id")
public class Ta extends User{
    private int Num_of_que;
    private String College;
}
