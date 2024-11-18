package com.example.demo.controller;


import com.example.demo.model.Produto;
import com.example.demo.repository.IProdutoRepository;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private IProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Produto createProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto updateProduto(@PathVariable Long id, @RequestBody Produto produtoDetails) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto != null) {
            produto.setNome(produtoDetails.getNome());
            produto.setCodigo(produtoDetails.getCodigo());
            produto.setQuantidade(produtoDetails.getQuantidade());
            produto.setPreco(produtoDetails.getPreco());
            produto.setMarca(produtoDetails.getMarca());
            return produtoRepository.save(produto);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }

    // endpoint para buscar por nome
    @GetMapping("/buscar/nome")
    public List<Produto> getProdutosByNome(@RequestParam String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }
    // endpoint para buscar por código
    @GetMapping("/buscar/codigo")
    public Produto getProdutosByCodigo(@RequestParam String codigo) {
        return produtoRepository.findByCodigo(codigo);
    }

    @PutMapping("/atualizar/quantidade")
    public ResponseEntity<Void> atualizarQuantidade(@RequestBody Produto produto) {
        try {
            produtoService.atualizarQuantidade(produto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }


}
