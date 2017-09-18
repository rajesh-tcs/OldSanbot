package com.qihancloud.librarydemo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 1407053 on 8/9/2017.
 */

public class MeasurmentBean {

    @SerializedName("x")
    @Expose
    private Double x;
    @SerializedName("y")
    @Expose
    private Double y;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
