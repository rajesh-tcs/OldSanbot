package com.qihancloud.librarydemo.utils;

/**
 * Created by 1407053 on 8/23/2017.
 */

public class DistanceBwPoint {
        public static void main(String arg[])
        {
            double x1,x2,y1,y2;
            double dis;
           // x1=2.021279296875;
            //y1=-1.9664256924746752;
            x2 = 3.2392187499999996;
            y2= -3.2095267392013476;
            x1=1.6588671874999998;
            y1=-2.8758580726864533;
            dis=Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
            System.out.println("distancebetween"+"("+x1+","+y1+"),"+"("+x2+","+y2+")===>"+dis);
            System.out.println("(int) Math.abs(dis) :::::: " + (int) Math.abs(dis));
        }
    }
