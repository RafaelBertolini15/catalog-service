package br.edu.fag.catalogservice.controller;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id){
        super("Produto não encontrado para o ID: " + id);
    }
}
