package com.example.demo.controller;


import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @GetMapping("/buscar/codigo")
    public Produto getProdutosByCodigo(@RequestParam String codigo) {
        return produtoService.getByCodigo(codigo);
    }


    @PostMapping
    public Produto createProduto(@RequestBody Produto produto) {
        return produtoService.save(produto);
    }


    @GetMapping("/buscar/nome")
    public List<Produto> getProdutosByNome(@RequestParam String nome) {
        return produtoService.findByNome(nome);
    }


    @PutMapping("/atualizar/quantidade")
    public ResponseEntity<Void> atualizarQuantidade(@RequestBody Produto produto) {
        produtoService.atualizarQuantidade(produto);
        return ResponseEntity.ok().build();

    }


}
