package com.travelport.ResponseMapping.baseEntity;

import com.travelport.ResponseMapping.baseEntity.FareInfo;

import java.util.List;

/**
 * Created by jiww on 2017/10/17.
 */
public class Farelist {
    private List<FareInfo> farelist;

    public List<FareInfo> getFarelist() {
        return farelist;
    }

    public void setFarelist(List<FareInfo> farelist) {
        this.farelist = farelist;
    }
}
