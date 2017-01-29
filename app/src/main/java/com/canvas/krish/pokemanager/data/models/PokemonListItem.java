package com.canvas.krish.pokemanager.data.models;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Immutable model class for a Pokemon
 */
public final class PokemonListItem implements Parcelable{
    private String name;
    private int id;
    private String type1;
    private String type2;
    private String description;
    private int backgroundColor;
    private int secondaryColor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    @Override
    public String toString() {
        String string = String.format("%d: %s", id, name);
        return string;
    }

    public PokemonListItem(){}

    private PokemonListItem (Parcel in){
        name = in.readString();
        id = in.readInt();
        type1 = in.readString();
        type2 = in.readString();
        description = in.readString();
        backgroundColor = in.readInt();
        secondaryColor = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeString(type1);
        dest.writeString(type2);
        dest.writeString(description);
        dest.writeInt(backgroundColor);
        dest.writeInt(secondaryColor);
    }

    public static final Parcelable.Creator<PokemonListItem> CREATOR = new Parcelable.Creator<PokemonListItem>(){
        @Override
        public PokemonListItem createFromParcel(Parcel source) {
            return new PokemonListItem(source);
        }

        @Override
        public PokemonListItem[] newArray(int size) {
            return new PokemonListItem[size];
        }
    };
}
