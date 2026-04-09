package com.codewithjona.sistemalibros.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminTestController {

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Solo Admin puede ver esto");
    }
}
