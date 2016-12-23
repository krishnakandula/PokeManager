package com.canvas.krish.pokemanager.data.models;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

/**
 * Immutable model class for a Pokemon
 */
public final class Pokemon {
    private String name;
    private String url;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String string = String.format("%d: %s", id, name);
        return string;
    }
}
