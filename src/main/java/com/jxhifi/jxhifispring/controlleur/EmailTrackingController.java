package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.emails.EmailScheduler;
import com.jxhifi.jxhifispring.emails.EmailService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTrackingController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailScheduler emailScheduler;

    @PostMapping("/start-tracking")
    public String startTracking(@RequestBody TrackingRequest request) {
        emailScheduler.trackOrder(request.getEmail(),request.getOrderId());
        return "Suivi commencé pour la commande.";
    }
}

@Getter @Setter
class TrackingRequest {
    private String email;
    private String orderId;

}
