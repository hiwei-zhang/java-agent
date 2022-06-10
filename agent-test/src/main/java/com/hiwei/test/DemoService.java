package com.hiwei.test;

public class DemoService {
    public static int add(int a,int b){
        return a+b;
    }
    public static String delete(){
        return "delete";
    }

    public static User getUser(){
        User user = new User();
        return user;
    }
}
