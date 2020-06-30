package com.example.myapplication.cart;

public class MyCustomObject {
    public interface MyCustomObjectListener {
        // These methods are the different events and
        // need to pass relevant arguments related to the event triggered
        // or when data has been loaded
        public void onadd(int index, int val);
        public void onsub(int index, int val);
        public void onDelete(int index);
    }
    private static MyCustomObjectListener listener;
    //    RecylerviewActivity object2=new RecylerviewActivity();
    public MyCustomObject(MyCustomObjectListener listener) {
        // set null or default listener or accept as argument to constructor
        this.listener = listener;
    }

    // Assign the listener implementing events interface that will receive the events
    public void setCustomObjectListener(MyCustomObjectListener listener) {
        this.listener = listener;
    }

    public void add(int index, int value) {

        if (listener != null) {
            System.out.println("kjvghfebgi"+listener);
//            object2.incr_decr();
            listener.onadd(index,value); // <---- fire listener here
        }
    }

    public void subtract(int index, int value) {
        if (listener != null) {
            System.out.println("kjvghfebgi"+value);
            listener.onsub(index,value); // <---- fire listener here
        }
    }
    public void detele(int index){
        if (listener != null) {
            System.out.println("kjvghfebgi"+index);
            listener.onDelete(index); // <---- fire listener here
        }
    }
}
