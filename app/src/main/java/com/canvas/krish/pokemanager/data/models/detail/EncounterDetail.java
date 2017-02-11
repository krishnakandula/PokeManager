package com.canvas.krish.pokemanager.data.models.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 2/11/2017.
 */ //-----------------------------------com.canvas.krish.pokemanager.data.models.EncounterDetail.java-----------------------------------
public class EncounterDetail {

    @SerializedName("min_level")
    @Expose
    private Integer minLevel;
    @SerializedName("max_level")
    @Expose
    private Integer maxLevel;
    @SerializedName("condition_values")
    @Expose
    private List<ConditionValue> conditionValues = null;
    @SerializedName("chance")
    @Expose
    private Integer chance;
    @SerializedName("method")
    @Expose
    private Method method;

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public List<ConditionValue> getConditionValues() {
        return conditionValues;
    }

    public void setConditionValues(List<ConditionValue> conditionValues) {
        this.conditionValues = conditionValues;
    }

    public Integer getChance() {
        return chance;
    }

    public void setChance(Integer chance) {
        this.chance = chance;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
