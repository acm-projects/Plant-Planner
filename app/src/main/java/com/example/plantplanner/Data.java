package com.example.plantplanner;

public class Data {
    private String id = "";
    private String common_name = "";

    public Data(String id, String common_name) {
        this.id = id;
        this.common_name = common_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

}


