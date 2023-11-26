package com.example.plantplanner;

public class Plant {
    private String common_name;
    private String scientific_name;
    private String plant_image;
    private String waterFrequency;
    private int id;


    // Constructor
    public Plant(String common_name, String sci_name, String plant_image, int id) {
        this.common_name = common_name;
        this.scientific_name = sci_name;
        this.plant_image = plant_image;
        this.id = id;
        this.waterFrequency = "none";
    }

    public Plant(String common_name, String sci_name, int id) {
        this.common_name = common_name;
        this.scientific_name = sci_name;
        this.plant_image = "https://perenual.com/storage/species_image/2_abies_alba_pyramidalis/regular/49255769768_df55596553_b.jpg";
        this.id = id;
        this.waterFrequency = "none";
    }

    public Plant(String common_name, String sci_name, String plant_image, int id, String waterFrequency) {
        this.common_name = common_name;
        this.scientific_name = sci_name;
        this.plant_image = plant_image;
        this.id = id;
        this.waterFrequency = waterFrequency;
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

    public String getWaterFrequency(){ return waterFrequency; }

    public void setWaterFrequency(String water){ this.waterFrequency = water; }
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
