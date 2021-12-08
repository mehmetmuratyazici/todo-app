package io.mmy.todoapp.controller;

import io.mmy.todoapp.model.Mession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class MessionController {

    @PostMapping
    private ResponseEntity<?> createMession(@RequestBody Mession mession){

        return ResponseEntity.ok("");
    }

}
