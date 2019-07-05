package com.example.restTest.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rest_db")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
}
