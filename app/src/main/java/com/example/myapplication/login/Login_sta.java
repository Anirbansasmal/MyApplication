package com.example.myapplication.login;


import java.util.List;

public class Login_sta {
    private Login_val login_val;

//    private String[] usr_otp;

    private String message;

    private String status;


    public Login_val getLogin_val ()
    {
        return login_val;
    }

    public void setLogin_val (Login_val login_val)
    {
        this.login_val = login_val;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [login_val = "+login_val+", message = "+message+", status = "+status+"]";
    }
}
