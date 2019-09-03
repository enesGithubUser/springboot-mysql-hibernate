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
@RequestMapping("/api/v1/makinalar")
@Slf4j
@RequiredArgsConstructor
public class MakinaAPI {
	
    @Autowired
    private MakinaRepository makinaRepository;
    
    @GetMapping(path="/all")
	public @ResponseBody List<Makina> getAllUsers() {
		// This returns a JSON or XML with the users
		return makinaRepository.findAll();
	}

    @GetMapping
    public ResponseEntity<List<Makina>> findAll() {
        return ResponseEntity.ok(makinaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Makina makina) {
        return ResponseEntity.ok(makinaRepository.save(makina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Makina> findById(@PathVariable Long id) {
        Optional<Makina> stock = makinaRepository.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Makina> update(@PathVariable Long id, @Valid @RequestBody Makina makina) {
        if (!makinaRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(makinaRepository.save(makina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!makinaRepository.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        makinaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
