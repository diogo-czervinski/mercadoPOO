package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.repository.IProdutoRepository;
import org.apache.logging.log4j.util.InternalException;
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

        try {
            Produto produtoExistente = (Produto) produtoRepository.findByCodigo(produtoAtualizado.getCodigo());
            if (produtoExistente != null) {
                produtoExistente.setQuantidade(produtoExistente.getQuantidade() - produtoAtualizado.getQuantidade());
                if(produtoExistente.getQuantidade()<0){
                 throw new RuntimeException("Produto Sem estoque");
                }
                produtoRepository.save(produtoExistente);
            } else {
                throw new RuntimeException("Produto não encontrado");
            }
        } catch (Exception e) {
            throw new InternalException("Deu ruim");
        }
    }

    public Produto save(Produto produto) {

        try{
            return produtoRepository.save(produto);
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
