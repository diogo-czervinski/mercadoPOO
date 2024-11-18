package com.example.demo.repository;



import com.example.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByCodigo(String codigo);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}

