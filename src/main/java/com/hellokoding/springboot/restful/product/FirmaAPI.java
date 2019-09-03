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
@RequestMapping("/api/v1/firmalar")
@Slf4j
@RequiredArgsConstructor
public class FirmaAPI {
	
    @Autowired
    private FirmaRepository firmaRepository;
    
    @GetMapping(path="/all")
	public @ResponseBody List<Firma> getAllUsers() {
		// This returns a JSON or XML with the users
		return firmaRepository.findAll();
	}

    @GetMapping
    public ResponseEntity<List<Firma>> findAll() {
        return ResponseEntity.ok(firmaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Firma firma) {
        return ResponseEntity.ok(firmaRepository.save(firma));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Firma> findById(@PathVariable Long id) {
        Optional<Firma> stock = firmaRepository.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Firma> update(@PathVariable Long id, @Valid @RequestBody Firma firma) {
        if (!firmaRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(firmaRepository.save(firma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!firmaRepository.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        firmaRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
