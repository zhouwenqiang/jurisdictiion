package com.jurisdiction;

import com.jurisdiction.common.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class Test {
   /* public static void main(String[] args) {
    int a= (int) (110.067460f*1000000);
        float b= 110.06746f;
        double c= 110.067460*1000000;
        BigDecimal d1=new BigDecimal(Float.toString(b));
        BigDecimal d2=new BigDecimal("1000000");
        d1.multiply(d2);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        //int a=d1.multiply(d2).doubleValue();
        System.out.println(d1.multiply(d2).intValue());
*//*
        Integer j = 100;
        Integer i = new Integer(100);
        System.out.print(i == j); //false


         Integer a=2;
        Integer b=1;
        System.out.println("a="+a+"-------b="+b);
        try {
            swap(a,b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("a="+a+"-------b="+b);*//*

    }*/
   public static void swap(Integer a,Integer b) throws Exception{
        Integer temp=new Integer(a);
       Field i1Value = a.getClass().getDeclaredField("value");
       i1Value.setAccessible(true);
       i1Value.set(a, b.intValue());
       Field i2Value = b.getClass().getDeclaredField("value");

       i2Value.setAccessible(true);
       i2Value.set(b, temp);
   }
    @Autowired
    GeneratorService generatorService;



}
