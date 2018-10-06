package com.store.stationary.controller;

import com.store.stationary.model.Product;
import com.store.stationary.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/stationary-store")
public class StationaryStoreController {

    @Autowired private ProductService service;

    @GetMapping
    public List<Product> getProducts(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable String id){
        return service.findOne(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody @Valid Product product){
        return service.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody @Valid Product product){
        product.setId(id);
        return service.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id){
        service.delete(id);
    }
}
