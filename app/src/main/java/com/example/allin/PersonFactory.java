package com.example.allin;

public class PersonFactory {

    public static Person GetPerson(String PersonType)
    {
        if (PersonType.equalsIgnoreCase("user"))
            return new User();
        else if(PersonType.equalsIgnoreCase("admin"))
            return new Admin();

        return null;
    }


}
