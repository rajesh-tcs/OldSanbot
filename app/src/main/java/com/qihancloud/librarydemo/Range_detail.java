package com.qihancloud.librarydemo;

/**
 * Created by 1407053 on 9/6/2017.
 */

public class Range_detail {

    public static void main(String[] args) {

        double dist_1 = 0.2;

        System.out.println(Math.abs(dist_1));


        System.out.println(Math.round(dist_1));

        if( Math.abs(dist_1) == 0)
        {

            System.out.println("I am zeroooo");
        }else{

            System.out.println("I am not zeroooo");
        }
    }
}