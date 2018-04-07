package com.travelport.ResponseMapping.baseEntity;

import com.travelport.ResponseMapping.baseEntity.Retreat;

import java.util.List;

/**
 * Created by jiww on 2017/10/17.
 */
public class Retreatlist {
    protected List<Retreat> Retreat;

    public List<com.travelport.ResponseMapping.baseEntity.Retreat> getRetreat() {
        return Retreat;
    }

    public void setRetreat(List<com.travelport.ResponseMapping.baseEntity.Retreat> retreat) {
        Retreat = retreat;
    }
}
