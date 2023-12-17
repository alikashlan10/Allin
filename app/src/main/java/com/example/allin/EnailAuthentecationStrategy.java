package com.example.allin;

public class EnailAuthentecationStrategy implements AuthenticationStrategy{


    @Override
    public boolean authenticate(String input,String username) {

        boolean found=false;
        OnlineShoppingSystem sys=OnlineShoppingSystem.getInstance();
        for (User user:sys.users) {
            if (user.UserName.equals(username)){
                if (user.getEmail().equals(input))
                {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
}
