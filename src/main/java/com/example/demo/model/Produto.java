package com.example.demo;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String codigo;
    private int quantidade;
    private double preco;
    
}
