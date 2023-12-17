package com.example.allin;

public class SsnAuthenticationStrategy implements AuthenticationStrategy{

    @Override
    public boolean authenticate(String input, String username) {
        boolean found=false;
        OnlineShoppingSystem sys=OnlineShoppingSystem.getInstance();
        for (User user:sys.users) {
            if (user.UserName.equals(username)){
                if (user.getSSN().equals(input))
                {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
}
