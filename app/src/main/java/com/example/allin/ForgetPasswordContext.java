package com.example.allin;

public class ForgetPasswordContext {
    private AuthenticationStrategy authenticationStrategy;

    public void setAuthenticationStrategy(AuthenticationStrategy strategy) {
        this.authenticationStrategy = strategy;
    }

    public boolean executetAuthenticationStrategy(String userInput,String username) {
        if (authenticationStrategy == null) {
            throw new IllegalStateException("Authentication strategy not set");
        }
        return authenticationStrategy.authenticate(userInput,username);
    }
}
