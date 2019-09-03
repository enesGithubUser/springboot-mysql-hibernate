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
@RequestMapping("/api/v1/iplikRengi")
@Slf4j
@RequiredArgsConstructor
public class IplikRengiAPI {
	
    @Autowired
    private IplikRengiRepository iplikRengiRepository;
    
    @GetMapping(path="/all")
	public @ResponseBody List<IplikRengi> getAllUsers() {
		// This returns a JSON or XML with the users
		return iplikRengiRepository.findAll();
	}

    @GetMapping
    public ResponseEntity<List<IplikRengi>> findAll() {
        return ResponseEntity.ok(iplikRengiRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody IplikRengi iplikRengi) {
        return ResponseEntity.ok(iplikRengiRepository.save(iplikRengi));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IplikRengi> findById(@PathVariable Long id) {
        Optional<IplikRengi> stock = iplikRengiRepository.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<IplikRengi> update(@PathVariable Long id, @Valid @RequestBody IplikRengi iplikRengi) {
        if (!iplikRengiRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(iplikRengiRepository.save(iplikRengi));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!iplikRengiRepository.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        iplikRengiRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}