package com.qihancloud.librarydemo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 1407053 on 8/9/2017.
 */

public class MeasurmentDataBean {

    @SerializedName("data")
    @Expose
    private MeasurmentBean measurmentBean;


    public MeasurmentBean getMeasurmentBean() {
        return measurmentBean;
    }

    public void setMeasurmentBean(MeasurmentBean measurmentBean) {
        this.measurmentBean = measurmentBean;
    }
}
