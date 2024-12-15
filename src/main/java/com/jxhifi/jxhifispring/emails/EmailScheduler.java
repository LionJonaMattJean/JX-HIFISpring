package com.jxhifi.jxhifispring.emails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    // Map pour stocker l'état de progression des emails pour chaque commande
    private Map<String, Integer> emailProgress = new HashMap<>();

    // Ajout d'une commande à la file d'attente
    public void trackOrder(String recipientEmail, String orderId) {
        // Initialisation de la commande avec la première étape (étape 0)
        emailProgress.put(orderId, 0);
    }

    // Méthode planifiée pour envoyer des emails toutes les 10 secondes
    @Scheduled(fixedRate = 10000)
    public void sendScheduledEmails() {
        for (Map.Entry<String, Integer> entry : emailProgress.entrySet()) {
            String orderId = entry.getKey();
            int step = entry.getValue();

            String recipientEmail = getEmailForOrder(orderId); // Obtenir l'email du client
            String subject = "Suivi de votre commande";
            String body = "";

            // Déterminer le contenu de l'email basé sur l'étape
            switch (step) {
                case 0:
                    body = createFirstEmail();
                    break;
                case 1:
                    body = createSecondEmail();
                    break;
                case 2:
                    body = createThirdEmail();
                    break;
                case 3:
                    body = createFourthEmail();
                    break;
                default:
                    // Si toutes les étapes sont terminées, supprimer la commande
                    emailProgress.remove(orderId);
                    continue;
            }

            // Envoyer l'email
            emailService.sendEmail(recipientEmail, subject, body);
            System.out.println("Email envoyé pour la commande : " + orderId + " - Étape : " + step);

            // Mettre à jour la progression
            emailProgress.put(orderId, step + 1);
        }
    }

    // Obtenir l'email du client basé sur l'ID de commande
    private String getEmailForOrder(String orderId) {
        // Logique pour trouver l'email associé à l'ID de commande
        // Exemple temporaire :
        return "client@example.com";
    }

    // Contenu des emails
    private String createFirstEmail() {
        return "Votre commande est prête à être envoyée !";
    }

    private String createSecondEmail() {
        return "Votre commande quitte l'entrepôt en direction du centre de tri.";
    }

    private String createThirdEmail() {
        return "Votre commande est en route !";
    }

    private String createFourthEmail() {
        return "Votre commande vient d'être livrée à votre porte !";
    }
}
