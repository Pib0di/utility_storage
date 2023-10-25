package com.thewhite.study.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class UtilityStorage {
    public UtilityStorage(Integer id, String name, String description, String link) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("link")
    public String link;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilityStorage that = (UtilityStorage) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, link);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ",\nname='" + name + '\'' +
                ",\ndescription='" + description + '\'' +
                ",\nlink='" + link + '\'';
    }
}
