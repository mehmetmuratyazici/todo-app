package io.mmy.todoapp.controller;

import io.mmy.todoapp.model.Mession;
import io.mmy.todoapp.service.MessionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class MessionController {

    @Autowired
    private MessionService messionService;

    @PostMapping
    private ResponseEntity<?> createMession(@RequestBody Mession mession){

        return ResponseEntity.ok(messionService.createMession(mession).toString());
    }

    @GetMapping
    private ResponseEntity<?> getMessions(@RequestParam Integer userId){
        return ResponseEntity.ok(messionService.getMessions(userId));
    }


}
