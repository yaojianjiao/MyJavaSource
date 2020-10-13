package com.yjj.test;

import java.util.function.Function;
import java.util.function.Supplier;

public class Test {
    public static void main(String[] args) {
        new Test().test();
    }
    
    
    
    public void test() {
        Object obj=new Object();
        User user=new User();
        user.setAge("29");
        user.setName("ÕÅÈý");
        
        Supplier<String> u=user::getName;
        String age=u.get();
        System.out.println("11-:"+u);
        System.out.println("11age-:"+age);
        
        Supplier<String> s =()->user.getName();
        System.out.println("lamdba::"+s.get());
        
        Function<Integer,String[]> fun =String[]::new;
        
        Function<Integer,String[]> fun1=(x)->{return new String[x];};
        
        
    }
    
}


class User{
    String name;
    String pwd;
    String age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    
}
