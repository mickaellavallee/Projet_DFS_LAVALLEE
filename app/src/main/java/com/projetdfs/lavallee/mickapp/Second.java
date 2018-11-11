package com.projetdfs.lavallee.mickapp;

public class Second {

    private String image;
    private String type;
    private String titre;
    private Double distance;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getTitre() {

        return titre;
    }

    public void setTitre(String titre) {

        this.titre = titre;
    }

    public Double getDistance() {

        return distance;
    }

    public void setDistance(Double distance) {

        this.distance = distance;
    }

    public Second(String image, String type,String titre, Double distance) {
        this.image = image;
        this.type = type;
        this.titre = titre;
        this.distance = distance;
    }
}