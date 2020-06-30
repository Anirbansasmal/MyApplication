package com.example.myapplication.profile;

public class User {
    private String u_id;

    private String UserName;

    private String UserPhone;

    private String __v;

    private String UserPin;

    private String UserAddress;

    private String _id;

    private String age;

    private String email;

    public String getU_id ()
    {
        return u_id;
    }

    public void setU_id (String u_id)
    {
        this.u_id = u_id;
    }

    public String getUserName ()
    {
        return UserName;
    }

    public void setUserName (String UserName)
    {
        this.UserName = UserName;
    }

    public String getUserPhone ()
    {
        return UserPhone;
    }

    public void setUserPhone (String UserPhone)
    {
        this.UserPhone = UserPhone;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public String getUserPin ()
    {
        return UserPin;
    }

    public void setUserPin (String UserPin)
    {
        this.UserPin = UserPin;
    }

    public String getUserAddress ()
    {
        return UserAddress;
    }

    public void setUserAddress (String UserAddress)
    {
        this.UserAddress = UserAddress;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [u_id = "+u_id+", UserName = "+UserName+", UserPhone = "+UserPhone+", __v = "+__v+", UserPin = "+UserPin+", UserAddress = "+UserAddress+", _id = "+_id+", age = "+age+", email = "+email+"]";
    }
}
