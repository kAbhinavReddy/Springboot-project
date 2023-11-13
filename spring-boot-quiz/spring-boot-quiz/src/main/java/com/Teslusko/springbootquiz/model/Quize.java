package com.Teslusko.springbootquiz.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class Quize {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String title;
    @ManyToMany
    private List<Question> question;
}
