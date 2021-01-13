package com.example.vroomrr;

public class SearchFilter {
    private String user_id;
    private int build_year_min;
    private int build_year_max;
    private int horsepower_min;
    private String fuel_types;
    private String brands;
    private String colors;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getBuild_year_min() {
        return build_year_min;
    }

    public void setBuild_year_min(int build_year_min) {
        this.build_year_min = build_year_min;
    }

    public int getBuild_year_max() {
        return build_year_max;
    }

    public void setBuild_year_max(int build_year_max) {
        this.build_year_max = build_year_max;
    }

    public int getHorsepower_min() {
        return horsepower_min;
    }

    public void setHorsepower_min(int horsepower_min) {
        this.horsepower_min = horsepower_min;
    }

    public String getFuel_types() {
        return fuel_types;
    }

    public void setFuel_types(String fuel_types) {
        this.fuel_types = fuel_types;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }
}
