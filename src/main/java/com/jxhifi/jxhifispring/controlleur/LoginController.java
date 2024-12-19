package com.jxhifi.jxhifispring.controlleur;


import com.jxhifi.jxhifispring.DTO.customer.CustomerDTO;
import com.jxhifi.jxhifispring.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerDTO loginRequest) {
        System.out.println("Requête reçue : " + loginRequest.getEmail() + " / " + loginRequest.getPassword());

        boolean isValid = loginService.validateCustomerCredentials(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        if (isValid) {
            return ResponseEntity.ok("{\"message\":\"Connexion réussie\"}");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"Email ou mot de passe incorrect\"}");
        }
    }
}
