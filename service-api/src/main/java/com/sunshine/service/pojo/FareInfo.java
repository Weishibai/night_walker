package com.sunshine.service.pojo;

import java.io.Serializable;

/**
 * Created by jiww on 2017/10/17.
 */
public class FareInfo implements Serializable {

    private static final long serialVersionUID = -4336754637036575315L;

    private String Type;//类型
    private int branchTotalPrice;
    private int branchBasePrice;
    private int branchTaxes;
    private String fareBasisCode;

    public FareInfo() {}

    public FareInfo(String type, int branchTotalPrice, int branchBasePrice, int branchTaxes, String fareBasisCode) {
        Type = type;
        this.branchTotalPrice = branchTotalPrice;
        this.branchBasePrice = branchBasePrice;
        this.branchTaxes = branchTaxes;
        this.fareBasisCode = fareBasisCode;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getBranchTotalPrice() {
        return branchTotalPrice;
    }

    public void setBranchTotalPrice(int branchTotalPrice) {
        this.branchTotalPrice = branchTotalPrice;
    }

    public int getBranchBasePrice() {
        return branchBasePrice;
    }

    public void setBranchBasePrice(int branchBasePrice) {
        this.branchBasePrice = branchBasePrice;
    }

    public int getBranchTaxes() {
        return branchTaxes;
    }

    public void setBranchTaxes(int branchTaxes) {
        this.branchTaxes = branchTaxes;
    }

    public String getFareBasisCode() {
        return fareBasisCode;
    }

    public void setFareBasisCode(String fareBasisCode) {
        this.fareBasisCode = fareBasisCode;
    }
}
