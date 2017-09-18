package com.qihancloud.librarydemo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 1407053 on 8/9/2017.
 */

public class EC03_Bean {



    @SerializedName("EC01")
    @Expose
    private Double eC01;
    @SerializedName("EC02")
    @Expose
    private Double eC02;

    public Double geteC01() {
        return eC01;
    }

    public void seteC01(Double eC01) {
        this.eC01 = eC01;
    }

    public Double geteC02() {
        return eC02;
    }

    public void seteC02(Double eC02) {
        this.eC02 = eC02;
    }
}
