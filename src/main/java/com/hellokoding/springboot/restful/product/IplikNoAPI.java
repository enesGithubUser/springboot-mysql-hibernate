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
@RequestMapping("/api/v1/iplikNo")
@Slf4j
@RequiredArgsConstructor
public class IplikNoAPI {
	
    @Autowired
    private IplikNoRepository iplikNoRepository;
    
    @GetMapping(path="/all")
	public @ResponseBody List<IplikNo> getAllUsers() {
		// This returns a JSON or XML with the users
		return iplikNoRepository.findAll();
	}

    @GetMapping
    public ResponseEntity<List<IplikNo>> findAll() {
        return ResponseEntity.ok(iplikNoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody IplikNo iplikNo) {
        return ResponseEntity.ok(iplikNoRepository.save(iplikNo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IplikNo> findById(@PathVariable Long id) {
        Optional<IplikNo> stock = iplikNoRepository.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<IplikNo> update(@PathVariable Long id, @Valid @RequestBody IplikNo iplikNo) {
        if (!iplikNoRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(iplikNoRepository.save(iplikNo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!iplikNoRepository.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        iplikNoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
