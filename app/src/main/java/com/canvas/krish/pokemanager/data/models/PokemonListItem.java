package com.canvas.krish.pokemanager.data.models;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

/**
 * Immutable model class for a Pokemon
 */
public final class PokemonListItem {
    private String name;
    private String sprite_front_default_uri;
    private int id;
    private String type1;
    private String type2;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprite_front_default_uri() {
        return sprite_front_default_uri;
    }

    public void setSprite_front_default_uri(String sprite_front_default_uri) {
        this.sprite_front_default_uri = sprite_front_default_uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String string = String.format("%d: %s", id, name);
        return string;
    }
}
