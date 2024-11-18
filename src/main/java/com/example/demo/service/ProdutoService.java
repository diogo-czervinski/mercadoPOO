package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository produtoRepository;

    public void atualizarQuantidade(Produto produtoAtualizado) {
        Produto produtoExistente = (Produto) produtoRepository.findByCodigo(produtoAtualizado.getCodigo());
        if (produtoExistente != null) {
            // Subtraindo a quantidade vendida da quantidade existente
            produtoExistente.setQuantidade(produtoExistente.getQuantidade() - produtoAtualizado.getQuantidade());
            produtoRepository.save(produtoExistente);
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }


}
