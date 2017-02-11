package com.canvas.krish.pokemanager.data.models;

/**
 * Created by Krishna Chaitanya Kandula on 12/27/2016.
 */
import com.canvas.krish.pokemanager.data.models.detail.Ability;
import com.canvas.krish.pokemanager.data.models.detail.Form;
import com.canvas.krish.pokemanager.data.models.detail.GameIndex;
import com.canvas.krish.pokemanager.data.models.detail.HeldItem;
import com.canvas.krish.pokemanager.data.models.detail.Move;
import com.canvas.krish.pokemanager.data.models.detail.Species;
import com.canvas.krish.pokemanager.data.models.detail.Sprites;
import com.canvas.krish.pokemanager.data.models.detail.Stat;
import com.canvas.krish.pokemanager.data.models.detail.Type;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//-----------------------------------com.canvas.krish.pokemanager.data.models.ConditionValue.java-----------------------------------

//-----------------------------------com.canvas.krish.pokemanager.data.models.Form.java-----------------------------------

//-----------------------------------com.canvas.krish.pokemanager.data.models.PokemonDetail.java-----------------------------------
public class PokemonDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("base_experience")
    @Expose
    private Integer baseExperience;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("is_default")
    @Expose
    private Boolean isDefault;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("abilities")
    @Expose
    private List<Ability> abilities = null;
    @SerializedName("forms")
    @Expose
    private List<Form> forms = null;
    @SerializedName("game_indices")
    @Expose
    private List<GameIndex> gameIndices = null;
    @SerializedName("held_items")
    @Expose
    private List<HeldItem> heldItems = null;
    @SerializedName("location_area_encounters")
    @Expose
    private String locationAreaEncounters = null;
    @SerializedName("moves")
    @Expose
    private List<Move> moves = null;
    @SerializedName("species")
    @Expose
    private Species species;
    @SerializedName("sprites")
    @Expose
    private Sprites sprites;
    @SerializedName("stats")
    @Expose
    private List<Stat> stats = null;
    @SerializedName("types")
    @Expose
    private List<Type> types = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public List<GameIndex> getGameIndices() {
        return gameIndices;
    }

    public void setGameIndices(List<GameIndex> gameIndices) {
        this.gameIndices = gameIndices;
    }

    public List<HeldItem> getHeldItems() {
        return heldItems;
    }

    public void setHeldItems(List<HeldItem> heldItems) {
        this.heldItems = heldItems;
    }

    public String getLocationAreaEncounters() {
        return locationAreaEncounters;
    }

    public void setLocationAreaEncounters(String locationAreaEncounters) {
        this.locationAreaEncounters = locationAreaEncounters;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder();
        toStringBuilder.append(getId() + " ").append(getName());
        return toStringBuilder.toString();
    }
}
