package com.example.allin;

public abstract class Person {

    String UserName;
    String password;
    int PersonID;

    public abstract int login(String username, String password);


    ////////////////////////// Setters and Getters //////////////////////////
    //----------------------------------------------------------------------

    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public int getPersonID() {
        return PersonID;
    }
    public void setPersonID(int personID) {
        PersonID = personID;
    }



    /////////////////////////////////////////////////////////////////////////
    //----------------------------------------------------------------------

}
