package com.canvas.krish.pokemanager.data.models.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Krishna Chaitanya Kandula on 2/11/2017.
 */ //-----------------------------------com.canvas.krish.pokemanager.data.models.GameIndex.java-----------------------------------
public class GameIndex {

    @SerializedName("game_index")
    @Expose
    private Integer gameIndex;
    @SerializedName("version")
    @Expose
    private Version version;

    public Integer getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(Integer gameIndex) {
        this.gameIndex = gameIndex;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

}
