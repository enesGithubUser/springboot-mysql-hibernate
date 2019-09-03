package com.hellokoding.springboot.restful.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductAPI {
	
    private ProductService productService;
    
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping(path="/all")
	public @ResponseBody List<Product> getAllUsers() {
		// This returns a JSON or XML with the users
		return productRepository.findAll();
	}

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productRepository.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> stock = productRepository.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        if (!productRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(productRepository.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!productRepository.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        productRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}