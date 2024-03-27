package br.com.gogar.sistema.api.domain.controller;


import br.com.gogar.sistema.api.domain.address.AddressFeignClient;
import br.com.gogar.sistema.api.domain.address.AddressRequest;
import br.com.gogar.sistema.api.domain.address.AddressResponse;
import br.com.gogar.sistema.api.domain.dto.UserPostDTO;
import br.com.gogar.sistema.api.domain.entity.User;
import br.com.gogar.sistema.api.domain.identification.IdenticationRequest;
import br.com.gogar.sistema.api.domain.identification.IdentificationFeingClient;
import br.com.gogar.sistema.api.domain.identification.IdentificationResponse;
import br.com.gogar.sistema.api.domain.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UsersService service;


    private final PasswordEncoder encoder;

    public UserController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping("/save")
    public ResponseEntity saveUser(@RequestBody User data) {
        data.setPassword(encoder.encode(data.getPassword()));

        var user = service.saveUserPost(data);

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/lists/all")
    public ResponseEntity listAllUsers(){
        var users = service.allListUsers();
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity listId(@PathVariable UUID id){
        var user = service.listUserId(id);
        return ResponseEntity.ok().body(user);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteId(@PathVariable UUID id){
        service.deleteUserId(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity updatePatch(@PathVariable UUID id, UserPostDTO data){
        var upDate = service.updatePatch(id, (Map<String, Object>) data);
        return ResponseEntity.ok().body(upDate);
    }
}
