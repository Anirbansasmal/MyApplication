package com.example.myapplication.dashbord;

public class Avl_times {
    private String DeliTimeSlot;

    private String __v;

    private String _id;

    private String pin_id;

    public String getDeliTimeSlot ()
    {
        return DeliTimeSlot;
    }

    public void setDeliTimeSlot (String DeliTimeSlot)
    {
        this.DeliTimeSlot = DeliTimeSlot;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getPin_id ()
    {
        return pin_id;
    }

    public void setPin_id (String pin_id)
    {
        this.pin_id = pin_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [DeliTimeSlot = "+DeliTimeSlot+", __v = "+__v+", _id = "+_id+", pin_id = "+pin_id+"]";
    }
}
