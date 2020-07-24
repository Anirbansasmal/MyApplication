package com.example.myapplication.login;


import java.util.ArrayList;

public class Login_val  {
    private String Regdate;

    private String phoneNumber;

    private ArrayList<String> usr_otp;

    private String otp;

    private String _id;

    private String time;

    public String getRegdate ()
    {
        return Regdate;
    }

    public void setRegdate (String Regdate)
    {
        this.Regdate = Regdate;
    }

    public String getPhoneNumber ()
    {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<String> getUsr_otp ()
    {
        return usr_otp;
    }

    public void setUsr_otp (ArrayList<String> usr_otp)
    {
        this.usr_otp = usr_otp;
    }

    public String getOtp ()
    {
        return otp;
    }

    public void setOtp (String otp)
    {
        this.otp = otp;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Regdate = "+Regdate+", phoneNumber = "+phoneNumber+", usr_otp = "+usr_otp+", otp = "+otp+", _id = "+_id+", time = "+time+"]";
    }
}
