package com.example.EmployeeManagement.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/normal")
    public ResponseEntity<String> normalUser(){
        return ResponseEntity.ok("I am normal user");
    } 
    @GetMapping("/admin")
    public ResponseEntity<String> adminUser(){
        return ResponseEntity.ok("I am admin user");
    } 
    @GetMapping("/public")
    public ResponseEntity<String> publicUser(){
        return ResponseEntity.ok("I am public user");
    } 
    
}
