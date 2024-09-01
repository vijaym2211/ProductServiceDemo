package com.example.ProductService.Joined_Table;

import jakarta.persistence.*;
import lombok.Data;

@Data
//@JoinTable
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "JT_User")
public class User {
    @Id
    private int user_id;
    private String name;
    private String email;
    private String password;
}
