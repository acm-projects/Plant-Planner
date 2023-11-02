package com.example.plantplanner;

public class plant {
    private String common_name;
    private String scientific_name;
    private int plant_image;

    // Constructor
    public plant(String common_name, String sci_name, int plant_image) {
        this.common_name = common_name;
        this.scientific_name = sci_name;
        this.plant_image = plant_image;
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
    public int getPlant_image() {
        return plant_image;
    }

    public void setPlant_image(int image) {
        this.plant_image = image;
    }
}
