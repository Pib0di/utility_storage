package com.thewhite.study.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class UtilityStorage {
    public UtilityStorage() {}

    public UtilityStorage(Integer id, String name, String description, String link) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, link);
    }

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("link")
    public String link;
}
