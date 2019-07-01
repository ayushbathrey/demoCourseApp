package com.example.experiment6.ModelClass;

public class GetFile {
    private String description;
    private String image;
    private String search;
    private String title;
    private String uploads;

    public GetFile(){

    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getSearch() {
        return search;
    }

    public String getTitle() {
        return title;
    }

    public String getUploads() {
        return uploads;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUploads(String uploads) {
        this.uploads = uploads;
    }
}
