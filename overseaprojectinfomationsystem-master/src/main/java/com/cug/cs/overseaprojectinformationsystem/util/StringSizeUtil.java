package com.cug.cs.overseaprojectinformationsystem.util;
/**
 * @author shentong
 * @date 2023/9/18 18:10
 * @description
 */
public class StringSizeUtil {

    public static String stringSize(String a){

        if (a.length()<2){
            return "0"+a;
        }else {
            return a;
        }
    }
    public static void main(String[] args) {

        System.out.println(stringSize("6"));
        System.out.println(stringSize("11"));
    }
}

