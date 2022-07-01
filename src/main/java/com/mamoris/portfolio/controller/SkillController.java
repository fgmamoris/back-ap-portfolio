/*
 */
package com.mamoris.portfolio.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Federico Mamoris
 */
@RestController
@RequestMapping("/api/skill")
@CrossOrigin(origins="http://localhost:4200")
public class SkillController {

    @GetMapping("/skills")
    @ResponseBody
    public ResponseEntity<?> getAll() {
        Map<String, Object> response = new HashMap<String, Object>();

        return new ResponseEntity<>("Get list", HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody String userDTO) {
        System.out.println("Ingreso");
        Map<String, Object> response = new HashMap<>();
        return new ResponseEntity<>("Create" + userDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<String, Object>();

        return new ResponseEntity<>("Get id", HttpStatus.FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody String userDTO) {
        Map<String, Object> response = new HashMap<>();

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        return new ResponseEntity<>("Delete", HttpStatus.CREATED);
    }
}
