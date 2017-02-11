package com.canvas.krish.pokemanager.data.models.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 2/11/2017.
 */ //-----------------------------------com.canvas.krish.pokemanager.data.models.HeldItem.java-----------------------------------
public class HeldItem {

    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("version_details")
    @Expose
    private List<VersionDetail> versionDetails = null;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<VersionDetail> getVersionDetails() {
        return versionDetails;
    }

    public void setVersionDetails(List<VersionDetail> versionDetails) {
        this.versionDetails = versionDetails;
    }

}
