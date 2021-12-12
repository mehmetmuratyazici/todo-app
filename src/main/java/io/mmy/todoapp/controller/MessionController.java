package io.mmy.todoapp.controller;

import io.mmy.todoapp.dto.MessionDto;
import io.mmy.todoapp.model.Mession;
import io.mmy.todoapp.service.mession.MessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class MessionController {

    @Autowired
    private MessionServiceImpl messionServiceImpl;

    @PostMapping
    private ResponseEntity<?> createMession(@RequestBody MessionDto messionDto){

        return ResponseEntity.ok(messionServiceImpl.createMession(messionDto).toString());
    }

    @GetMapping
    private ResponseEntity<?> getMessions(@RequestParam String username){
        return ResponseEntity.ok(messionServiceImpl.getMessions(username));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateMession(@PathVariable String id){
        return ResponseEntity.ok(messionServiceImpl.updateMession(id).toString());
    }

    @DeleteMapping("{id}")
    private ResponseEntity<?> daleteMession(@PathVariable String id){
        return ResponseEntity.ok(messionServiceImpl.deleteMession(id).toString());
    }
}
