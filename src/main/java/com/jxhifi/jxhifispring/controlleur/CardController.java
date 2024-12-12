package com.jxhifi.jxhifispring.controlleur;

import com.jxhifi.jxhifispring.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;
}
