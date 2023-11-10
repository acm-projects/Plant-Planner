package com.example.plantplanner;

public class Plant {
    private String common_name;
    private String scientific_name;
    private String plant_image;
    private int id;


    // Constructor
    public Plant(String common_name, String sci_name, String plant_image, int id) {
        this.common_name = common_name;
        this.scientific_name = sci_name;
        this.plant_image = plant_image;
        this.id = id;
    }

    // Getter and Setter
    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String name) {
        this.common_name = name;
    }

    public String getScientific_name(){
        return scientific_name;
    }
    public void setScientific_name(String name){
        this.scientific_name = name;
    }
    public String getPlant_image() {
        return plant_image;
    }

    public void setPlant_image(String image) {
        this.plant_image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
