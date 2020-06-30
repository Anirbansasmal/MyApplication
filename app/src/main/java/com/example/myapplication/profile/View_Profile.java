package com.example.myapplication.profile;

import java.util.List;

public class View_Profile {
    public List<User> user;

    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getUser ()
    {
        return user;
    }

    public void setUser (List<User> user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [user = "+user+"]";
    }
}
