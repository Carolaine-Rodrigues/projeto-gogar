package br.com.gogar.sistema.api.domain.controller;

import br.com.gogar.sistema.api.domain.dto.ServicesDTO;
import br.com.gogar.sistema.api.domain.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    ClassService service;

    @PostMapping("/save")
    public ResponseEntity postService(@RequestBody ServicesDTO data){
        var post = service.saveServices(data);
        return ResponseEntity.ok(post);
    }
    @GetMapping("/list/all")
    public ResponseEntity getAllService(){
        var gets = service.ListAllServices();
        return ResponseEntity.ok(gets);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity getId(@PathVariable Long id){
        var get = service.listId(id);
        return ResponseEntity.ok(get);
    }
    @PutMapping("/update/all/{id}")
    public ResponseEntity putUpdateAll(@PathVariable Long id, @RequestBody ServicesDTO data){
        var updates = service.updateAll(id, data);
        return ResponseEntity.ok(updates);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity patchId(@PathVariable Long id, @RequestBody ServicesDTO data){
        var updateId = service.updateAll(id, data);
        return ResponseEntity.ok(updateId);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteId(@PathVariable Long id){
        service.deleteId(id);
        return ResponseEntity.noContent().build();
    }
}
