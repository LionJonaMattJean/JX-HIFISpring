package com.jxhifi.jxhifispring.emails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 10000)
    public void sendScheduledEmail() {
        String recipientEmail = ""; // aller chercher le courriel du client
        String subject = "Suivi de votre commande";
        String body = "";
        int counter = 0;

        //logic to get the right email to send
        for(int i = 0; i < 4; i++){
            if(i == 1 && counter == 0){
                body = createFirstEmail();
            }else if(i == 2 && counter == 1){
                body = createSecondEmail();
            }else if(i == 3 && counter == 2){
                body = createThirdEmail();
            }else{
                body = createFourthEmail();
            }
            counter++;
            i++;
        }
        emailService.sendEmail(recipientEmail, subject, body);
    }



    //Body of each 4 emails to send
    private String createFirstEmail(){
        return "Votre commande est prêt à être envoyée!";
    }
    private String createSecondEmail(){
        return "Votre commande quitte l'entrepôt en direction du centre de tri";
    }
    private String createThirdEmail(){
        return "Votre Commande est en route!";
    }
    private String createFourthEmail(){
        return "votre commande vient d'être livrée à votre porte!";
    }
}
