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
@RequestMapping("/api/v1/iplikCinsi")
@Slf4j
@RequiredArgsConstructor
public class IplikCinsiAPI {
	
    @Autowired
    private IplikCinsiRepository iplikCinsiRepository;
    
    @GetMapping(path="/all")
	public @ResponseBody List<IplikCinsi> getAllUsers() {
		// This returns a JSON or XML with the users
		return iplikCinsiRepository.findAll();
	}

    @GetMapping
    public ResponseEntity<List<IplikCinsi>> findAll() {
        return ResponseEntity.ok(iplikCinsiRepository.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody IplikCinsi iplikCinsi) {
        return ResponseEntity.ok(iplikCinsiRepository.save(iplikCinsi));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IplikCinsi> findById(@PathVariable Long id) {
        Optional<IplikCinsi> stock = iplikCinsiRepository.findById(id);
        if (!stock.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<IplikCinsi> update(@PathVariable Long id, @Valid @RequestBody IplikCinsi iplikCinsi) {
        if (!iplikCinsiRepository.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(iplikCinsiRepository.save(iplikCinsi));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!iplikCinsiRepository.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        iplikCinsiRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
