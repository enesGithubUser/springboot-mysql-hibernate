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
@RequestMapping("/api/v1/musteriler")
@Slf4j
@RequiredArgsConstructor
public class MusteriAPI {
	
    @Autowired
    private MusteriRepository musteriRepository;
    
    @GetMapping(path="/all")
	public @ResponseBody List<Musteri> getAllUsers() {
		// This returns a JSON or XML with the users
		return musteriRepository.findAll();
	}

    @GetMapping
    public ResponseEntity<List<Musteri>> findAll() {
        return ResponseEntity.ok(musteriRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Musteri musteri) {
        return ResponseEntity.ok(musteriRepository.save(musteri));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musteri> findById(@PathVariable Long id) {
        Optional<Musteri> stock = musteriRepository.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musteri> update(@PathVariable Long id, @Valid @RequestBody Musteri musteri) {
        if (!musteriRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(musteriRepository.save(musteri));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!musteriRepository.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        musteriRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}