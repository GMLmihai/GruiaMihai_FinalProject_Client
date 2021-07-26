package com.company.interaction;

import com.company.service.authenticator.AuthenticatorImpl;
import com.company.service.authenticator.Authentificator;
import com.company.exceptions.InvalidClientInteractionException;

import java.util.Scanner;

import static com.company.util.constants.ChatroomAPI.LOGIN;
import static com.company.util.constants.ChatroomAPI.REGISTER;

public class ClientRegisterLoginImpl implements ClientInteractionService{
    Scanner scanner = new Scanner(System.in);
    Authentificator authenticator = new AuthenticatorImpl();

    @Override
    public void initInteraction() {

        switch (chooseInitialAction()) {
            case LOGIN:
                authenticator.login();
                break;
            case REGISTER:
                authenticator.register();
                initInteraction();
                break;
        }
    }

    private Integer chooseInitialAction() {

        System.out.println(" CHATROOM PROJECT ");
        System.out.println("Choose action: ");
        System.out.println("Register - press " + REGISTER);
        System.out.println("Login - press " + LOGIN);
        System.out.println("------------------------");

        try {
            int action = Integer.parseInt(scanner.nextLine());
            if (action != LOGIN && action != REGISTER) {
                throw new InvalidClientInteractionException();
            }
            return action;
        } catch (Exception ex) {
            System.out.println("Enter a valid number: " + LOGIN + " (LOGIN) " +
                    " or " + REGISTER + " (REGISTER) ");
        }
        return chooseInitialAction();
    }


}

