package com.jxhifi.jxhifispring.controlleur;


import com.jxhifi.jxhifispring.DTO.customer.CustomerDTO;
import com.jxhifi.jxhifispring.entities.User;
import com.jxhifi.jxhifispring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerDTO loginRequest) {
        User user = customerService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        System.out.println("Requête reçue : " + loginRequest.getEmail() + " / " + loginRequest.getPassword());

        if (user != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Connexion réussie");
            response.put("customerId", user.getId());
            response.put("token", generateToken(user));

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Utilisateur ou mot de passe incorrect"));
        }
    }

    private String generateToken(User user) {
        return "generated-token";
    }
}
