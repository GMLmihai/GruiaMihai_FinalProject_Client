package com.company;

import com.company.interaction.ClientInteractionImpl;
import com.company.interaction.ClientInteractionService;
import com.company.interaction.ClientRegisterLoginImpl;

public class Main {

    public static void main(String[] args) {

        ClientRegisterLoginImpl registerLogin = new ClientRegisterLoginImpl();
        registerLogin.initInteraction();

        ClientInteractionService clientInteractionService = new ClientInteractionImpl();
        clientInteractionService.initInteraction();

    }
}

