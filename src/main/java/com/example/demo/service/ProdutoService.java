package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository produtoRepository;


    public Produto getByCodigo(String codigo){
        try{
            return produtoRepository.findByCodigo(codigo);
        }catch (Exception e){
            throw new RuntimeException("Deu merda aqui"+e.getMessage());
        }
    }


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


    public Produto save(Produto produto) {

        try{
            return produtoRepository.save(produto);
        }catch (Exception e){
            throw new RuntimeException("Deu merda aqui"+e.getMessage());
        }
    }

    public void deleteById(Long id) {
        try{
            produtoRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Deu merda aqui"+e.getMessage());
        }
    }


    public List<Produto> findByNome(String nome) {

        try{
            return produtoRepository.findByNomeContainingIgnoreCase(nome);
        }catch (Exception e){
            throw new RuntimeException("Deu merda aqui"+e.getMessage());
        }

    }
}
