 package com.softeem.util;

 public class Ddgdgff {
     public static String get2Decimals(int value) {
         int temp = value / 100;
         if (0 == temp && value < 0) {
             return String.format("-%d.%02d", temp, (Math.abs(value - temp * 100)));
         } else {
             return String.format("%d.%02d", temp, (Math.abs(value - temp * 100)));
         }
     }
     
     public static void main(String[] args) {
         
         
      
         
    }

}
