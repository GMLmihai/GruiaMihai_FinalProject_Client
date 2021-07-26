package com.company.token;

import com.company.service.authenticator.AuthenticatorImpl;

import java.util.ArrayList;

public class TokenServices {
    public static String getToken() {
        String token = AuthenticatorImpl.getToken();
        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(token);
        for (String str : tokens) {
            return str;
        }
        return null;
    }
}
