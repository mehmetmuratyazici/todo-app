package io.mmy.todoapp.controller;

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
    private ResponseEntity<?> createMession(@RequestBody Mession mession){

        return ResponseEntity.ok(messionServiceImpl.createMession(mession).toString());
    }
    @GetMapping(path = "/hi")
    private ResponseEntity<?> success(){
        return ResponseEntity.ok("Hi How Are you ?");
    }

    @GetMapping
    private ResponseEntity<?> getMessions(@RequestParam Integer userId){
        return ResponseEntity.ok(messionServiceImpl.getMessions(userId));
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
