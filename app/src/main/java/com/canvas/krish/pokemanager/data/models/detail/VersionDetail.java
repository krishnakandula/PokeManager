package com.canvas.krish.pokemanager.data.models.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Krishna Chaitanya Kandula on 2/11/2017.
 */ //-----------------------------------com.canvas.krish.pokemanager.data.models.VersionDetail.java-----------------------------------
public class VersionDetail {

    @SerializedName("rarity")
    @Expose
    private Integer rarity;
    @SerializedName("version")
    @Expose
    private Version_ version;

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Version_ getVersion() {
        return version;
    }

    public void setVersion(Version_ version) {
        this.version = version;
    }

}
