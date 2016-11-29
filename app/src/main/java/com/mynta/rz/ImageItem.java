package com.mynta.rz;

/**
 * Created by jrizvan on 11/29/16.
 */

public class ImageItem {

    private int position;

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    private Boolean isVisited;

    public void setVisited(Boolean visited) {
        isVisited = visited;
    }

    public Boolean getVisited() {
        return isVisited;
    }

    private String imagePath;

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

}
