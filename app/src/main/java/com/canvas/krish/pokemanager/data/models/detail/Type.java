package com.canvas.krish.pokemanager.data.models.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Krishna Chaitanya Kandula on 2/11/2017.
 */ //-----------------------------------com.canvas.krish.pokemanager.data.models.Type.java-----------------------------------
public class Type {

    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("type")
    @Expose
    private Type_ type;

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Type_ getType() {
        return type;
    }

    public void setType(Type_ type) {
        this.type = type;
    }

}
